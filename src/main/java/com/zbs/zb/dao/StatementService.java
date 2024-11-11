package com.zbs.zb.dao;

import com.zbs.zb.db_model.OracleStatement;
import com.zbs.zb.db_model.OracleStatementDetail;
import com.zbs.zb.db_model.Statement;
import com.zbs.zb.db_model.StatementDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

import static com.zbs.zb.constants.Constants.*;

@Service
public class StatementService {

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;;

    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    /*
    * closing_balance, credit_amount, debit_amount, opening_balance, id, uid, account_ccy, account_number, branch,
    * customer_name, instrument_no, narrative, transaction_date, transaction_desc, transaction_ref_no, value_date, sdid
    *
    *
    * uid, closing_balance, opening_balance, status_code, id, account_ccy, account_number, account_type,
    *  customer_name, message, statement_period, status
    * */

    public String insertStatementData(Statement statement){
        String sql = "INSERT INTO statement (uid,closing_balance, opening_balance, status_code, " +
                "account_ccy, account_number, account_type, customer_name, message, statement_period, status) VALUES" +
                " (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            int rowsAffected = primaryJdbcTemplate.update(sql,
                    statement.getUID(),
                    statement.getCLOSING_BALANCE(),
                    statement.getOPENING_BALANCE(),
                    statement.getSTATUS_CODE(),
                    statement.getACCOUNT_CCY(),
                    statement.getACCOUNT_NUMBER(),
                    statement.getACCOUNT_TYPE(),
                    statement.getCUSTOMER_NAME(),
                    statement.getMESSAGE(),
                    statement.getSTATEMENT_PERIOD(),
                    statement.getSTATUS());
            if (rowsAffected > 0) {
                return "1";
            } else {
                return "0";
            }
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }


    /*
    * closing_balance, credit_amount, debit_amount, opening_balance, uid, account_ccy, account_number, branch,
     * customer_name, instrument_no, narrative, transaction_date, transaction_desc, transaction_ref_no, value_date, sdid
    * */
    public String insertStatementDetailData(StatementDetail statementDetail){
        String sql = "INSERT INTO statement_detail (closing_balance, credit_amount, debit_amount, opening_balance, uid, account_ccy, account_number, branch," +
                "customer_name, instrument_no, narrative, transaction_date, transaction_desc, transaction_ref_no, value_date, sdid) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            int rowsAffected = primaryJdbcTemplate.update(sql,
                    statementDetail.getCLOSING_BALANCE(),
                    statementDetail.getCREDIT_AMOUNT(),
                    statementDetail.getDEBIT_AMOUNT(),
                    statementDetail.getOPENING_BALANCE(),
                    statementDetail.getUID(),
                    statementDetail.getACCOUNT_CCY(),
                    statementDetail.getACCOUNT_NUMBER(),
                    statementDetail.getBRANCH(),
                    statementDetail.getCUSTOMER_NAME(),
                    statementDetail.getINSTRUMENT_NO(),
                    statementDetail.getNARRATIVE(),
                    statementDetail.getTRANSACTION_DATE(),
                    statementDetail.getTRANSACTION_DESC(),
                    statementDetail.getTRANSACTION_REF_NO(),
                    statementDetail.getVALUE_DATE(),
                    statementDetail.getSDID()
                    );
            if (rowsAffected > 0) {
                return "1";
            } else {
                return "0";
            }
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    public String insertStatementOracleDB(OracleStatement oracleStatement){
        String sql = "INSERT INTO CE_STATEMENT_HEADERS_INT (STATEMENT_NUMBER,BANK_ACCOUNT_NUM, STATEMENT_DATE, BANK_NAME, BANK_BRANCH_NAME" +
                ", CONTROL_BEGIN_BALANCE, CONTROL_END_BALANCE, RECORD_STATUS_FLAG, CURRENCY_CODE, ORG_ID) VALUES " +
                "(?,?,TO_DATE(?, 'YYYY-MM-dd'),?,?,?,?,?,?,?)";

        try{

            int rowsAffected = secondaryJdbcTemplate.update(sql,
                    oracleStatement.getSTATEMENT_NUMBER(),
                    oracleStatement.getBANK_ACCOUNT_NUM(),
                    oracleStatement.getSTATEMENT_DATE(),
                    oracleStatement.getBANK_NAME(),
                    oracleStatement.getBANK_BRANCH_NAME(),
                    oracleStatement.getCONTROL_BEGIN_BALANCE(),
                    oracleStatement.getCONTROL_END_BALANCE(),
                    oracleStatement.getRECORD_STATUS_FLAG(),
                    oracleStatement.getCURRENCY_CODE(),
                    oracleStatement.getORG_ID()
                    );

            System.out.println("row aff " + rowsAffected);
            if (rowsAffected > 0) {
                return "1";
            } else {
                return "0";
            }

        }catch (DataAccessException e){
            System.out.println("err oracle " + e.getMessage());
            return e.getMessage();
        }
    }

    /*
    * insert statement detail to oracle DB
    * */

    public String insertStatementDetailOracleDB(OracleStatementDetail oracleStatementDetail){
        String sql = "INSERT INTO CE_STATEMENT_LINES_INTERFACE (BANK_ACCOUNT_NUM, STATEMENT_NUMBER, LINE_NUMBER, TRX_DATE, " +
                "TRX_CODE, EFFECTIVE_DATE, TRX_TEXT, AMOUNT, CURRENCY_CODE, BANK_TRX_NUMBER, CREATED_BY, CREATION_DATE, ATTRIBUTE2, BANK_ACCT_CURRENCY_CODE) VALUES (" +
                "?,?,?,TO_DATE(?, 'YYYY-MM-dd'),?,TO_DATE(?, 'YYYY-MM-dd'),?,?,?,?,?,TO_DATE(?, 'YYYY-MM-dd'),?,?)";

        try{
            int rowsAffected = secondaryJdbcTemplate.update(sql,
                    oracleStatementDetail.getBANK_ACCOUNT_NUM(),
                    oracleStatementDetail.getSTATEMENT_NUMBER(),
                    oracleStatementDetail.getLINE_NUMBER(),
                    oracleStatementDetail.getTRX_DATE(),
                    oracleStatementDetail.getTRX_CODE(),
                    oracleStatementDetail.getEFFECTIVE_DATE(),
                    oracleStatementDetail.getTRX_TEXT(),
                    oracleStatementDetail.getAMOUNT(),
                    oracleStatementDetail.getCURRENCY_CODE(),
                    oracleStatementDetail.getBANK_TRX_NUMBER(),
                    oracleStatementDetail.getCREATED_BY(),
                    oracleStatementDetail.getCREATION_DATE(),
                    oracleStatementDetail.getATTRIBUTE2(),
                    oracleStatementDetail.getBANK_ACCT_CURRENCY_CODE()
            );

            System.out.println("row aff " + rowsAffected);
            if (rowsAffected > 0) {
                return "1";
            } else {
                return "0";
            }
        }catch (DataAccessException e){
            System.out.println("err oracle " + e.getMessage());
            return e.getMessage();
        }
    }

    /*
    * get statement header id and latest date
    * */

    public String getStatementHeaderIdAndLatestDate(){
        /*
            return latest date and statement header id
         */
        AtomicReference<String> result = new AtomicReference<>("-1");
        try {
            String sql = "SELECT STATEMENT_NUMBER, STATEMENT_DATE FROM CE_STATEMENT_HEADERS_INT ORDER BY STATEMENT_DATE DESC FETCH FIRST 1 ROW ONLY";

            secondaryJdbcTemplate.query(sql, rs -> {
                    result.set(rs.getString("STATEMENT_NUMBER") + "@" + rs.getDate("STATEMENT_DATE").toString());
            });

            return result.get();
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println("statement number and last in date " + result.get());
        return  result.get();
    }

    public String get_statement_interface_line_number(){
        AtomicReference<String> result = new AtomicReference<>("1");
        try{
            String sql = "SELECT MAX(LINE_NUMBER) AS LINE_NUMBER FROM CE_STATEMENT_LINES_INTERFACE";
            //String sql = "SELECT LINE_NUMBER FROM CE_STATEMENT_LINES_INTERFACE ORDER BY TRX_DATE DESC FETCH FIRST 1 ROW ONLY";
            secondaryJdbcTemplate.query(sql, rs -> {
                result.set(rs.getString("LINE_NUMBER"));
            });
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(result.get() == null){
            return "1";
        }
        return result.get();
    }

    public int compareDate(String db_date, String api_date){
        /*
        * compare date and return
        * 0 => if the dates are exactly the same
        * 1 => just add statement line, ignore statement header
        * 2 => add both statement header and statement line
        * 3 => invalid date
        * */

        /*
        * get each date and split using "-" put it on array
        * */

        String[] db_date_arr = db_date.split("-");
        String[] api_date_arr = api_date.split("-");

        /*
        * construct local date
        * */

        //db_date_arr[0], db_date_arr[1], db_date_arr[2]
        LocalDate db_local_date_format = LocalDate.of(Integer.parseInt(db_date_arr[0]), Integer.parseInt(db_date_arr[1]), Integer.parseInt(db_date_arr[2]));
        LocalDate api_local_date_format = LocalDate.of(Integer.parseInt(api_date_arr[0]),Integer.parseInt(api_date_arr[1]),Integer.parseInt(api_date_arr[2]));

        /*
        * start Comparesion
        * */

        if(db_date.equals(api_date)){
            return BOTH_DATES_ARE_EQUAL;
        }

        if(api_local_date_format.getYear() < db_local_date_format.getYear()){
            return INVALID_DATE;
        }

        /*
        * if dates equal in year but different in month
        * */

        if((api_local_date_format.getYear() == db_local_date_format.getYear()) && (db_local_date_format.getMonthValue() < api_local_date_format.getMonthValue())){
            return DAY_MONTH_DIFFERENCE;
        }

        /*
        * if db day is greater than api day
        * */

        if((api_local_date_format.getMonthValue() == db_local_date_format.getMonthValue()) && (api_local_date_format.getDayOfMonth() < db_local_date_format.getDayOfMonth())){
            return DAY_DIFFERENCE;
        }

        /*
        * if api date is one month after db date and api day of the month is start day of the month
        * create new statement header record
        * */

        if((db_local_date_format.plusMonths(1).getMonth() == api_local_date_format.getMonth()) &&
                (api_local_date_format.getDayOfMonth() == 1)){
            return DAY_MONTH_DIFFERENCE;
        }
        return INVALID_DATE;
    }

    public int compareDate_(String db_date, String api_date){
        /*
        * 0 => exact the same date
        * 1 => just add statement line, ignore statement header
        * 2 => add both statement header and statement line
        * 3 => invalid date
        * */
        String[] db_date_arr = db_date.split("-");
        String[] api_date_arr = api_date.split("-");

        LocalDate db_local_date_format = LocalDate.of(Integer.parseInt(db_date_arr[0]), Integer.parseInt(db_date_arr[1]), Integer.parseInt(db_date_arr[2]));
        LocalDate api_local_date_format = LocalDate.of(Integer.parseInt(api_date_arr[0]),Integer.parseInt(api_date_arr[1]),Integer.parseInt(api_date_arr[2]));

        if(db_date.equals(api_date)){
            return BOTH_DATES_ARE_EQUAL;
        }

        if(db_local_date_format.getYear() != api_local_date_format.getYear()){
            return INVALID_DATE;
        }

        /*
         * if api day is greater than db day (day only)
         * */

        if((api_local_date_format.getMonthValue() == db_local_date_format.getMonthValue()) && (api_local_date_format.getDayOfMonth() > db_local_date_format.getDayOfMonth())){
            return DAY_DIFFERENCE;
        }

        /*
         * if api date is one month after db date and api day of the month is start day of the month
         * create new statement header record
         * */
        if((db_local_date_format.plusMonths(1).getMonth() == api_local_date_format.getMonth()) &&
                (api_local_date_format.getDayOfMonth() == 1)){
            return DAY_MONTH_DIFFERENCE;
        }

        return INVALID_DATE;
    }

}


