
package homework3;

public class Item {
    
    String itemTitle;
    String category;

    public Item(String item, String cat) {
        this.itemTitle = item;
        this.category = cat;
    }

    public String getItem() {
        return itemTitle;
    }

    public void setItem(String item) {
        this.itemTitle = item;
    }

    public String getCat() {
        return category;
    }

    public void setCat(String type) {
        this.category = type;
    }
    

    
}
