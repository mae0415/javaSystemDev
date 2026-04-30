// 小垣幸流
package bean;
 
// ユーザー用
public class User {
 
    // ログインしてるかどうか
    private boolean isAuthenticated;
 
    // getter（確認用）
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
 
    // setter（設定用）
    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}