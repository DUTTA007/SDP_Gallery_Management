public class User {
private String username;
private String TableName;

    private char[] password;
    
public User(String username, char[] password, String TableName) {
        this.username = username;
        this.password = password;
        this.TableName = TableName;
    }
public String getUsername() {
        return username;
    }
public void setUsername(String username) {
        this.username = username;
    }
public char[] getPassword() {
        return password;
    }
public void setPassword(char[] password) {
        this.password = password;
    }
public String getTableName() {
    return TableName;
}
public void TableName(String TableName) {
    this.TableName = TableName;
}
}