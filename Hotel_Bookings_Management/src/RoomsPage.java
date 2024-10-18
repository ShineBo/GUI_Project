import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomsPage extends JFrame implements ActionListener {
    private JTable roomTable;
    private static DefaultTableModel tableModel;
    static ArrayList<Room> roomList = new ArrayList<>();
    private JMenu homeMenu, roomsMenu;

    private static final String ALL_ROOMS = "All Rooms";
    private static final String STANDARD_ROOM = "Standard Room";
    private static final String DELUXE_ROOM = "Deluxe Room";
    private static final String AVAILABLE_ROOMS = "Available Rooms";

    public RoomsPage() {
        setTitle("Hotel Booking Management - Rooms");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        homeMenu = new JMenu("Home");

        JMenuItem goToHome = new JMenuItem("Go to Main Menu");
        goToHome.addActionListener(this);
        homeMenu.add(goToHome);

        roomsMenu = new JMenu("Rooms");

        JMenuItem allRoomsItem = new JMenuItem(ALL_ROOMS);
        allRoomsItem.setActionCommand(ALL_ROOMS);
        allRoomsItem.addActionListener(this);

        JMenuItem standardRoomItem = new JMenuItem(STANDARD_ROOM);
        standardRoomItem.setActionCommand(STANDARD_ROOM);
        standardRoomItem.addActionListener(this);

        JMenuItem deluxeRoomItem = new JMenuItem(DELUXE_ROOM);
        deluxeRoomItem.setActionCommand(DELUXE_ROOM);
        deluxeRoomItem.addActionListener(this);

        JMenuItem availableRoomsItem = new JMenuItem(AVAILABLE_ROOMS);
        availableRoomsItem.setActionCommand(AVAILABLE_ROOMS);
        availableRoomsItem.addActionListener(this);

        roomsMenu.add(allRoomsItem);
        roomsMenu.add(standardRoomItem);
        roomsMenu.add(deluxeRoomItem);
        roomsMenu.add(availableRoomsItem);

        menuBar.add(homeMenu);
        menuBar.add(roomsMenu);

        setJMenuBar(menuBar);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        String[] columnNames = {"Room Number", "Room Floor", "Room Type", "Available", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        roomTable = new JTable(tableModel);
        roomTable.setBackground(new Color(254, 249, 217));
        roomTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(roomTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        loadRoomData(roomList);

        add(tablePanel);
        setVisible(true);
    }

    static void loadRoomData(ArrayList<Room> rooms) {
        tableModel.setRowCount(0);

        for (Room room : rooms) {
            String[] rowData = {
                    String.valueOf(room.getRoomNumber()),
                    String.valueOf(room.getRoomFloor()),
                    room.getRoomType(),
                    room.isRoomAvailable() ? "Yes" : "No",
                    String.format("$%.2f", room.getRoomPrice())
            };
            tableModel.addRow(rowData);
        }
    }

    private ArrayList<Room> filterRoomsByType(String type) {
        ArrayList<Room> filteredRooms = new ArrayList<>();
        for (Room room : roomList) {
            if (room.getRoomType().equals(type)) {
                filteredRooms.add(room);
            }
        }
        return filteredRooms;
    }

    private ArrayList<Room> filterAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : roomList) {
            if (room.isRoomAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Go to Main Menu")) {
            this.dispose();
            new MainPage();
        } else {
            switch (command) {
                case ALL_ROOMS:
                    loadRoomData(roomList);
                    break;

                case STANDARD_ROOM:
                    loadRoomData(filterRoomsByType("Standard"));
                    break;

                case DELUXE_ROOM:
                    loadRoomData(filterRoomsByType("Deluxe"));
                    break;

                case AVAILABLE_ROOMS:
                    loadRoomData(filterAvailableRooms());
                    break;

                default:
                    System.out.println("Invalid action command");
                    break;
            }
        }
    }
    public static void main(String[] args) {
        new RoomsPage();
    }
}