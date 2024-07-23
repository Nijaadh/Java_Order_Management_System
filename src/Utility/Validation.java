
package Utility;

public class Validation {
    public String[] loginValidation(String userName,String password){
        String status[];
        status = new String[]{"Access Denaid !!","block"};
        if((userName.equals("") && password.equals("") )|| (userName.equals("") && password.equals("***************")) || userName.equals("") || password.equals("")){
            status = new String[]{"Some fields are Empty !!","block"};
            return status;
        }
        else if(!userName.equals("abc") && !password.equals("123")){
            status = new String[]{"Cridentials does Not match !!","block"};
            return status;
        }
        else if(userName.equals("abc") && password.equals("123")){
            status = new String[]{"Access Garanted!!","granted"};
            return status;
        }
        else{
            System.out.println("no action");
        }
        return status;
        
    }
}
