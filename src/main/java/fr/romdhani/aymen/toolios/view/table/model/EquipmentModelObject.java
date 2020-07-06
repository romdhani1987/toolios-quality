package fr.romdhani.aymen.toolios.view.table.model;

import fr.romdhani.aymen.toolios.core.orm.Product;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EquipmentModelObject extends AbstractTableModel {
    private final List<Product> products = new ArrayList<Product>();

    private final String[] header = {"Id", "name", "Category", "Description", "Serialized properties"};

    public EquipmentModelObject() {
        super();
    }

    public int getRowCount() {
        return products.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return products.get(rowIndex).getId();
            case 1:
                return products.get(rowIndex).getTitle();
            case 2:
                return products.get(rowIndex).getArticle_category();
            case 3:
                return products.get(rowIndex).getDescription();
            case 4:
                return products.get(rowIndex).getSerialized_properties();
            default:
                return null;
        }
    }

    public void addAllProducts(List<Product> productList) {
        productList.forEach(user -> {
            if (!products.contains(user)) {
                products.add(user);
                fireTableRowsInserted(products.size() - 1, products.size() - 1);
            }
        });
    }

    public void addProduct(Product product) {
        products.add(product);
        fireTableRowsInserted(products.size() - 1, products.size() - 1);
    }

    public void deleteScreen(int rowIndex) {
        products.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Product getScreen(int i) {
        return products.get(i);
    }
}