package except;

/**
 * author  luhongtao
 * 2022/7/22 22:47:35
 **/
public class MyException extends Exception{
    public MyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
