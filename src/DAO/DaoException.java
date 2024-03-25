package DAO;

public class DaoException extends Exception {
    private int gravity;


    public DaoException(String message,int gravity) {
        super(message);
        this.gravity=gravity;
    }
    /**
     * Renvoie la gravité associée à l'exception.
     *
     * @return La valeur de la gravité associée à l'exception.
     */

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }
}
