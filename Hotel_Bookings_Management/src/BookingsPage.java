import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookingsPage extends JFrame implements ActionListener {
    private ArrayList<Booking> bookingList;
    private ArrayList<Room> roomList = RoomsPage.roomList;
    private JPanel currentBookingsPanel;
    private JPanel addBookingPanel;
    private JPanel panelSearch;
    private JLabel labelSearch;
    private CardLayout cardLayout;
    private JButton searchBtn;

    private JTable bookingTable;
    private static DefaultTableModel tableModel;
    private JTextField guestNameField, searchTextField;
    private JComboBox<String> roomComboBox;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;

    public BookingsPage() {
        bookingList = new ArrayList<>();
        addSampleBookings();

        setTitle("Hotel Booking Management");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu homeMenu = new JMenu("Home");
        JMenuItem goToHome = new JMenuItem("Go to Main Menu");
        goToHome.addActionListener(this);
        homeMenu.add(goToHome);

        JMenu bookingsMenu = new JMenu("Bookings");
        JMenuItem currentBookingsItem = new JMenuItem("Current Bookings");
        JMenuItem newBookingItem = new JMenuItem("New Booking");

        currentBookingsItem.setActionCommand("CURRENT_BOOKINGS");
        currentBookingsItem.addActionListener(this);

        newBookingItem.setActionCommand("NEW_BOOKING");
        newBookingItem.addActionListener(this);

        bookingsMenu.add(currentBookingsItem);
        bookingsMenu.add(newBookingItem);

        menuBar.add(homeMenu);
        menuBar.add(bookingsMenu);
        setJMenuBar(menuBar);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        createCurrentBookingsPanel();
        createAddBookingPanel();

        JPanel currentBookingsWithSearchPanel = new JPanel(new BorderLayout());
        currentBookingsWithSearchPanel.add(panelSearch, BorderLayout.NORTH);
        currentBookingsWithSearchPanel.add(currentBookingsPanel, BorderLayout.CENTER);

        add(currentBookingsWithSearchPanel, "CURRENT_BOOKINGS");
        add(addBookingPanel, "NEW_BOOKING");

        setVisible(true);
    }

    private void createCurrentBookingsPanel() {
        panelSearch = new JPanel();
        labelSearch = new JLabel("Search Booking");
        searchTextField = new JTextField(10);
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(this);
    
        panelSearch.setLayout(new FlowLayout());
        panelSearch.add(labelSearch);
        panelSearch.add(searchTextField);
        panelSearch.add(searchBtn);
        panelSearch.setBackground(new Color(210, 244, 251));
    
        currentBookingsPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Booking ID", "Guest Name", "Room", "Check-In", "Check-Out"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bookingTable = new JTable(tableModel);
        bookingTable.setRowHeight(30);
        bookingTable.setBackground(new Color(254, 249, 217));
    
        JScrollPane scrollPane = new JScrollPane(bookingTable);
        currentBookingsPanel.add(scrollPane, BorderLayout.CENTER);
    
        loadBookingData(bookingList);
    
        // Remove buttons related to active/cancel actions
    }

    private void createAddBookingPanel() {
        addBookingPanel = new JPanel();
        addBookingPanel.setLayout(new BoxLayout(addBookingPanel, BoxLayout.Y_AXIS));
        addBookingPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        addBookingPanel.setBackground(new Color(210, 244, 251));

        JLabel titleLabel = new JLabel("Add New Booking");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addBookingPanel.add(titleLabel);
        addBookingPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        guestNameField = createLabeledField("Guest Name:");
        roomComboBox = createLabeledComboBox("Room:", roomList);

        checkInDateField = createLabeledField("Check-In Date (yyyy-mm-dd):");
        checkOutDateField = createLabeledField("Check-Out Date (yyyy-mm-dd):");

        JButton addBookingButton = new JButton("Add Booking");
        addBookingButton.setActionCommand("ADD_BOOKING");
        addBookingButton.addActionListener(this);
        addBookingButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addBookingPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        addBookingPanel.add(addBookingButton);
    }

    private JTextField createLabeledField(String labelText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setMaximumSize(new Dimension(500, 40));
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField(15);
        panel.add(label);
        panel.add(textField);
        addBookingPanel.add(panel);
        return textField;
    }

    private JComboBox<String> createLabeledComboBox(String labelText, ArrayList<Room> rooms) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setMaximumSize(new Dimension(500, 40));
        JLabel label = new JLabel(labelText);
        JComboBox<String> comboBox = new JComboBox<>();
        for (Room room : rooms) {
            if (room.isRoomAvailable()) {
                comboBox.addItem(room.getRoomNumber() + " - " + room.getRoomType());
            }
        }
        panel.add(label);
        panel.add(comboBox);
        addBookingPanel.add(panel);
        return comboBox;
    }

    private void loadBookingData(ArrayList<Booking> bookings) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        tableModel.setRowCount(0);
        for (Booking booking : bookings) {
            String checkInDate = dateFormat.format(booking.getCheckInDate());
            String checkOutDate = dateFormat.format(booking.getCheckOutDate());
            String[] rowData = {
                    String.valueOf(booking.getBookingID()),
                    booking.getGuestName(),
                    booking.getRoom().getRoomType() + " (Room " + booking.getRoom().getRoomNumber() + ")",
                    checkInDate,
                    checkOutDate
            };
            tableModel.addRow(rowData);
        }
    }

    private void addSampleBookings() {
        Room room1 = new StandardRoom(101, 1, true, 100.00); // Change to true for available
        Room room2 = new DeluxeRoom(201, 2, true, 200.00); // Change to true for available
        bookingList.add(new Booking(1, room1, "John Doe", new Date(), new Date()));
        bookingList.add(new Booking(2, room2, "Jane Smith", new Date(), new Date()));
    }

    private void searchBookings() {
        String searchBooking = searchTextField.getText().toLowerCase();
        int found = 0;
        ArrayList<Booking> searchResultList = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getGuestName().toLowerCase().contains(searchBooking)) {
                found += 1;
                searchResultList.add(booking);
                loadSearchData(searchResultList);
            }
        }
        if (found == 0) {
            JOptionPane.showMessageDialog(this, "Booking Not Found.");
        }
    }

    private void loadSearchData(ArrayList<Booking> searchResultList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        tableModel.setRowCount(0);
        for (Booking booking : searchResultList) {
            String checkInDate = dateFormat.format(booking.getCheckInDate());
            String checkOutDate = dateFormat.format(booking.getCheckOutDate());
            String[] rowData = {
                    String.valueOf(booking.getBookingID()),
                    booking.getGuestName(),
                    booking.getRoom().getRoomType() + " (Room " + booking.getRoom().getRoomNumber() + ")",
                    checkInDate,
                    checkOutDate
            };
            tableModel.addRow(rowData);
        }
    }

    private void addNewBooking() {
        try {
            String guestName = guestNameField.getText();
            if (guestName.isEmpty()) {
                throw new IllegalArgumentException("Guest name cannot be empty.");
            }

            int roomIndex = roomComboBox.getSelectedIndex();
            if (roomIndex < 0) {
                throw new NoSuchElementException("No available rooms selected.");
            }
            Room selectedRoom = roomList.get(roomIndex);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = dateFormat.parse(checkInDateField.getText());
            Date checkOutDate = dateFormat.parse(checkOutDateField.getText());

            if (checkInDate.after(checkOutDate)) {
                throw new IllegalArgumentException("Check-Out date cannot be before Check-In date.");
            }

            Booking newBooking = new Booking(bookingList.size() + 1, selectedRoom, guestName, checkInDate, checkOutDate);
            bookingList.add(newBooking);

            guestNameField.setText("");
            checkInDateField.setText("");
            checkOutDateField.setText("");

            loadBookingData(bookingList);
            cardLayout.show(getContentPane(), "CURRENT_BOOKINGS");
            JOptionPane.showMessageDialog(this, "Added New Booking Successfully.");

        } catch (ParseException parseException) {
            JOptionPane.showMessageDialog(this, "Please enter valid dates in the format yyyy-MM-dd", "Invalid Date", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException illegalArgumentException) {
            JOptionPane.showMessageDialog(this, illegalArgumentException.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (NoSuchElementException noSuchElementException) {
            JOptionPane.showMessageDialog(this, noSuchElementException.getMessage(), "Room Selection Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
    
        switch (command) {
            case "Go to Main Menu":
                this.dispose();
                new MainPage();
                break;

            case "Search":
                searchBookings();
                searchTextField.setText("");
                break;
    
            case "CURRENT_BOOKINGS":
                cardLayout.show(getContentPane(), "CURRENT_BOOKINGS");
                loadBookingData(bookingList);
                break;
    
            case "NEW_BOOKING":
                cardLayout.show(getContentPane(), "NEW_BOOKING");
                break;
    
            case "ADD_BOOKING":
                addNewBooking();
                break;
        }
    }

    public static void main(String[] args) {
        new BookingsPage();
    }
}