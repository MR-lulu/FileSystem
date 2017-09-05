package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ClassName: Document 
 * @Description: TODO
 * @author Mr_blue
 * @date 2017年9月5日
 */
public class Document {
	private StringProperty name;
	private IntegerProperty type;
	private IntegerProperty size;
	private StringProperty owner;
	private StringProperty modifyTime;
	private StringProperty cteateTime;
	private IntegerProperty power;
	private final String[] agrs= {"文件夹","文件"};
	
	public Document(String name,int type,int size,String owner,
			String modifyTime, String cteateTime, int power) {
		super();
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleIntegerProperty(type);
		this.size = new SimpleIntegerProperty(size);
		this.owner = new SimpleStringProperty(owner);
		this.modifyTime = new SimpleStringProperty(modifyTime);
		this.cteateTime = new SimpleStringProperty(cteateTime);
		this.power = new SimpleIntegerProperty(power);
	}
	public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    
	public int getType() {
        return type.get();
    }
    public IntegerProperty typeProperty() {
        return type;
    }
    public void setType(int type) {
        this.type.set(type);
    }
    public String getTypeString(){
    	return agrs[type.get()];
    }
    
    public int getSize() {
        return size.get();
    }
    public IntegerProperty sizeProperty() {
        return size;
    }
    public void setSize(int size) {
        this.size.set(size);
    }
    
    public String getOwner() {
        return owner.get();
    }
    public StringProperty ownerProperty() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner.set(owner);
    }
    
    public String getModifyTime() {
        return modifyTime.get();
    }
    public StringProperty  modifyTimeProperty() {
        return modifyTime;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime.set(modifyTime);
    }
    
    public String getCteateTime() {
        return cteateTime.get();
    }
    public StringProperty cteateTimeProperty() {
        return cteateTime;
    }
    public void setCteateTime(String cteateTime) {
        this.cteateTime.set(cteateTime);
    }
    
    public int getPower() {
        return power.get();
    }
    public IntegerProperty powerProperty() {
        return power;
    }
    public void setPower(int power) {
        this.power.set(power);
    }
}
