package presentation.view;

import dao.AbstractDAO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Frame abstract care primeste ca si parametru abstract un tip
 * de obiect a carui date sa trebuiasca afisate intr-un tabel.
 *
 * <p>Dupa aceea, utilizatorul va alege una dintre cele 3
 * optiuni:
 *      - Editare
 *      -  Stergere
 *      - Inserare</p>
 *
 * <p>A fost folosuta metoda reflection pentru generarea
 * headerului tabelului si afisarea datelor despre obiecte</p>
 *
 * @param <T> tipul obiectului care trebuie afisat
 */

public class AbstractView<T> extends JFrame {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;


    private Container container;
    private JPanel panel;
    private JTable objectsTable;
    private JButton editButton, insertButton, deleteButton;

    private JLabel label;

    @SuppressWarnings("unchecked")
    public AbstractView(String title) {
        this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        setTitle(title);
        setSize(800, 600);
        setLocation(600, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(20, 20, 600, 500);
        panel.setBackground(new Color(48, 48, 48));
        panel.setForeground(new Color(48, 48, 48));

        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(48, 48, 48));

        label = new JLabel("Hello!");
        label.setBounds(700, 200, 100, 40);
        label.setForeground(Color.green);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Monospaced", Font.PLAIN, 14));

        objectsTable = new JTable();
        objectsTable.setBounds(20, 20, 600, 500);
        //objectsTable.setGridColor(new Color(155, 155, 155));
        objectsTable.setBackground(new Color(48, 48, 48));
        objectsTable.setForeground(new Color(155, 155, 155));
        objectsTable.setBorder(new LineBorder(new Color(155, 155, 155), 2));
        objectsTable.setRowHeight(20);
        objectsTable.setFont(new Font("Monospaced", Font.PLAIN, 14));
        objectsTable.getTableHeader().setBackground(new Color(48, 48, 48));
        objectsTable.getTableHeader().setForeground(new Color(155, 155, 155));



        editButton = new JButton("EDIT");
        editButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        editButton.setBounds(650, 310, 100, 40);
        editButton.setBorder(new LineBorder(new Color(0, 171, 125), 5));
        editButton.setBackground(new Color(0, 171, 125));
        editButton.setVerticalAlignment(JButton.CENTER);

        deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        deleteButton.setBounds(650, 230, 100, 40);
        deleteButton.setBorder(new LineBorder(new Color(169, 0, 86), 5));
        deleteButton.setBackground(new Color(169, 0, 86));
        deleteButton.setVerticalAlignment(JButton.CENTER);

        insertButton = new JButton("INSERT");
        insertButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        insertButton.setBounds(650, 150, 100, 40);
        insertButton.setBorder(new LineBorder(new Color(24, 168, 29), 5));
        insertButton.setBackground(new Color(24, 168, 29));
        insertButton.setVerticalAlignment(JButton.CENTER);

        JScrollPane scrollPane = new JScrollPane(objectsTable);
        panel.add(scrollPane);
        container.add(panel);
        container.add(editButton);
        container.add(deleteButton);
        container.add(insertButton);

        setVisible(true);
    }

    public void insertListener(ActionListener listenForInsert) {
        insertButton.addActionListener(listenForInsert);
    }

    public void deleteListener(ActionListener listenForDelete) {
        deleteButton.addActionListener(listenForDelete);
    }

    public void editListener(ActionListener listenForEdit) {
        editButton.addActionListener(listenForEdit);
    }

    /**
     * Aceasta metoda genereaza prin metoda reflection un
     * array de Stringuri care reprezinta denumirile coloanelor
     * tabelului, care corespund atributelor unui obiect, si o matrice
     * de obiecte care reprezinta datele ce trebuie afisate in tabel.
     *
     * @param objects o lista de obiecte de tip T ce trebuie inserate
     * @return matricea datelor ce trebuei afisate
     */

    public Object[][] setContentsTable(List<T> objects) {
        int nbFields = 0;
        for(Field field : type.getDeclaredFields()) {
            nbFields++;
        }
        String[] fields = new String[nbFields];
        for(int i=0;i<nbFields;i++) {
            fields[i] = type.getDeclaredFields()[i].getName();
        }
        Object[][] data = new Object[objects.size()][nbFields];

        for(int row =0; row<objects.size();row++) {
            for(int column=0;column<nbFields; column++) {
                try {
                    Field field= type.getDeclaredField(fields[column]);
                    field.setAccessible(true);
                    String value = field.get(objects.get(row)).toString();
                    data[row][column] = value;
                } catch (IllegalAccessException e) {
                    System.out.println("uwu");
                } catch (NoSuchFieldException e) {
                    System.out.println("owo");
                }
            }
        }
        DefaultTableModel tableModel = (DefaultTableModel) objectsTable.getModel();
        tableModel.setDataVector(data, fields);
        resizeColumnWidth(objectsTable);
        objectsTable.setModel(tableModel);
        objectsTable.getColumnModel().getColumn(0).setMinWidth(0);
        objectsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        return data;
    }

    /**
     * Metoda care returneaza datele obiectului al
     * carui rand a fost selectat.
     *
     *
     * @return un array de Stringuri care reprezinta datele selectate
     */

    public String[] getSelectedRow(){
        int columns = objectsTable.getColumnCount();
        int row = objectsTable.getSelectedRow();
        String[] value = new String[columns];
        for(int i=0;i<columns; i++) {
            value[i] = objectsTable.getModel().getValueAt(row, i).toString();
        }

        return value;
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

}
