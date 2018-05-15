package cn.anx.exceptions;

public class ShopOperationException extends RuntimeException{

    private static final long serialVersionUID = 279421094456357266L;

    public ShopOperationException(String msg){
        super(msg);
    }
}
