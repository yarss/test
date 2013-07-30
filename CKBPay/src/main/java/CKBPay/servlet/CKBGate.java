package CKBPay.servlet;

import CKBPay.domain.CKBPayInfo;
import CKBPay.mypackage.IHisoSOAPWebServicebindingStub;
import CKBPay.mypackage.IHisoSOAPWebServiceserviceLocator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Yarss
 * Date: 28.07.11
 */
public class CKBGate extends HttpServlet {
    private static Logger log = Logger.getLogger(CKBGate.class);
    private static Boolean worked = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private CKBPayInfo pay;
    private String payResult;
    PrintWriter out;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!worked) {
            worked = true;
            //request.setCharacterEncoding("utf-8");
            log.info("url=" + request.getRequestURI() + " request=" + request.getQueryString());
            if (request.getRequestURI().contains("favicon")) {
                worked = false;
                return;
            }
            try {
                pay = getPayInfo(request);
                payResult = doPay(pay);

                //System.out.println("payResult=" + payResult);
                log.info("payResult=" + payResult);

                out = response.getWriter();
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");

                out.println("<response>");
                out.println("<osmp_txn_id>" + request.getParameter("txn_id") + "</osmp_txn_id>");
                out.println("<prv_txn>" + request.getParameter("txn_id") + "</prv_txn>");
                out.println("<sum>" + request.getParameter("sum") + "</sum>");
                out.println("<result>" + payResult + "</result>");
                //out.println("<test1>" + payResult + "</test1>");
                out.println("</response>");
                out.close();
                //Thread.sleep(100000);
                worked = false;
            } catch (Exception e) {
                log.error(e.getMessage());
                //e.printStackTrace();
                worked = false;
            }

        }
    }

    public CKBPayInfo getPayInfo(HttpServletRequest request) {
        // ?command=check&txn_id=345347&txn_date=20110726115856&account=6&sum=10.00
        // prv_id trm_id
        // ?command=check&txn_id=345347&txn_date=20110726115856&account=6&sum=10.00&prv_id=MTEL&trm_id=12345
        // ?command=check&txn_id=345347&txn_date=20110726115856&account=068376333&sum=10&prv_id=MTEL&trm_id=T1001045&trm_name=DEONIT 1
        String command = request.getParameter("command");
        String txn_id = request.getParameter("txn_id");
        String txn_date = request.getParameter("txn_date");
        String account = request.getParameter("account");
        String sum = request.getParameter("sum");
        String prv_id = request.getParameter("prv_id");
        String trm_id = "M0000055";//"T1000940";//request.getParameter("trm_id");
        String trm_name = "10046";//request.getParameter("trm_name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        try {
            Date dt = sdf.parse(txn_date);
            cal.setTime(dt);
            cal.add(Calendar.HOUR, -2);
            txn_date = new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());
        } catch (ParseException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
        }

        CKBPayInfo pay = new CKBPayInfo(prv_id);

        // cTransactionID = 922 + Day(1 digit) + Right(cTerminalID, 4) + cInvoiceNo
        //String s = txn_date.substring(7, 8);
        pay.setTransactionID(new StringBuilder("922")
                .append(txn_date.substring(7, 8))
                //.append("9")
                .append(getLastnCharacters(trm_id, 4))
                .append(getLastnCharacters(txn_id, 6))
                .toString());
        pay.setInvoiceNo(getLastnCharacters(txn_id, 6));
        pay.setTerminalID(trm_id);
        pay.setTerminalName(trm_name);
        pay.setSubscriberID(account);
        pay.setAmount(sum);
        pay.setDateTime(txn_date.substring(2) + "00");

        return pay;
    }

    public String getLastnCharacters(String inputString, int subStringLength) {
        int length = inputString.length();
        if (length <= subStringLength) {
            return inputString;
        }
        int startIndex = length - subStringLength;
        return inputString.substring(startIndex);
    }

    //private IHisoSOAPWebService service;
    private IHisoSOAPWebServicebindingStub binding;

    public String doPay(CKBPayInfo payInfo) {
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");

        //service = new IHisoSOAPWebServiceservice().getIHisoSOAPWebServicePort();
        log.info(new StringBuilder("cUserID=").append(payInfo.getUserID())
                .append("\ncTransactionID=").append(payInfo.getTransactionID())
                .append("\ncInvoiceNo=").append(payInfo.getInvoiceNo())
                .append("\ncTransactionType=").append(payInfo.getTransactionType())
                .append("\ncRteMethod=").append(payInfo.getRteMethod())
                .append("\ncOperatorID=").append(payInfo.getOperatorID())
                .append("\ncDistributerID=").append(payInfo.getDistributerID())
                .append("\ncTerminalID=").append(payInfo.getTerminalID())
                .append("\ncTerminalName=").append(payInfo.getTerminalName())
                .append("\ncSubscriberID=").append(payInfo.getSubscriberID())
                .append("\niAmount=").append(payInfo.getAmount())
                .append("\ncProcessorID=").append(payInfo.getProcessorID())
                .append("\niDiler=").append(payInfo.getDealerID())
                .append("\ndDateTime=").append(payInfo.getDateTime())
        );

        /*System.out.println(new StringBuilder("cUserID=").append(payInfo.getUserID())
                .append("\ncTransactionID=").append(payInfo.getTransactionID())
                .append("\ncInvoiceNo=").append(payInfo.getInvoiceNo())
                .append("\ncTransactionType=").append(payInfo.getTransactionType())
                .append("\ncRteMethod=").append(payInfo.getRteMethod())
                .append("\ncOperatorID=").append(payInfo.getOperatorID())
                .append("\ncDistributerID=").append(payInfo.getDistributerID())
                .append("\ncTerminalID=").append(payInfo.getTerminalID())
                .append("\ncTerminalName=").append(payInfo.getTerminalName())
                .append("\ncSubscriberID=").append(payInfo.getSubscriberID())
                .append("\niAmount=").append(payInfo.getAmount())
                .append("\ncProcessorID=").append(payInfo.getProcessorID())
                .append("\niDiler=").append(payInfo.getDealerID())
                .append("\ndDateTime=").append(payInfo.getDateTime())
        );*/
        String responseCode = "";

        try {
            binding = (IHisoSOAPWebServicebindingStub) new IHisoSOAPWebServiceserviceLocator().getIHisoSOAPWebServicePort();
            responseCode = binding.topup(payInfo.getUserID(),
                    payInfo.getTransactionID(),
                    payInfo.getInvoiceNo(),
                    payInfo.getTransactionType(),
                    payInfo.getRteMethod(),
                    payInfo.getOperatorID(),
                    payInfo.getDistributerID(),
                    payInfo.getTerminalID(),
                    payInfo.getTerminalName(),
                    payInfo.getSubscriberID(),
                    new Integer(payInfo.getAmount()),
                    payInfo.getProcessorID(),
                    payInfo.getDealerID(),
                    payInfo.getDateTime());
        } catch (Exception e) {
            //e.printStackTrace();
            log.error(e.getMessage());
        }
        return responseCode;

    }


}
