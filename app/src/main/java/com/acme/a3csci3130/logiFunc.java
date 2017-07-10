package com.acme.a3csci3130;

import android.widget.Toast;

/**
 * Created by rainb on 2017/7/7.
 */

public class logiFunc {

    public static boolean checkPrimaryBusiness(String P){
        if(!P.equals("Fisher")&&!P.equals("Distributor")&&!P.equals("Processor")&&!P.equals("Fish Monger")&&!P.equals("fisher")&&!P.equals("distributor")&&!P.equals("processor")&&!P.equals("fish monger")){
            return false;
        }
        else
            return true;
    }


    public boolean checkProvince(String pro){
        if(!pro.equals("AB")&&!pro.equals("BC")&&!pro.equals("MB")&&!pro.equals("NB")&&!pro.equals("NL")&&!pro.equals("NS")&&!pro.equals("NT")&&!pro.equals("NU")&&!pro.equals("ON")&&!pro.equals("PE")&&!pro.equals("QC")&&!pro.equals("SK")&&!pro.equals("YT")&&!pro.equals("") && !pro.equals("ab")&&!pro.equals("bc")&&!pro.equals("mb")&&!pro.equals("nb")&&!pro.equals("nl")&&!pro.equals("ns")&&!pro.equals("nt")&&!pro.equals("nu")&&!pro.equals("on")&&!pro.equals("pb")&&!pro.equals("qc")&&!pro.equals("sk")&&!pro.equals("yt")){
            return false;
        }
        else
            return true;
    }
}
