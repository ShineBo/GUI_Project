import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame implements ActionListener{

    private static boolean sampleRoomsAdded = false;

    public MainPage() {
        if(!sampleRoomsAdded) {
            addSampleRooms();
            sampleRoomsAdded = true;
        }
        setTitle("Hotel Booking Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(254, 249, 217));
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(210, 244, 251));
        JLabel titleLabel = new JLabel("Hotel Booking Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(40, 40, 40));
        headerPanel.add(titleLabel);

        JButton roomsButton = new JButton("Rooms");
        JButton bookingsButton = new JButton("Bookings");
        JButton exitButton = new JButton("Exit");

        roomsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        bookingsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));

        Color buttonBackgroundColor = new Color(75, 75, 75);
        Color buttonTextColor = new Color(70, 70, 70);

        roomsButton.setBackground(buttonBackgroundColor);
        roomsButton.setForeground(buttonTextColor);
        roomsButton.addActionListener(this);

        bookingsButton.setBackground(buttonBackgroundColor);
        bookingsButton.setForeground(buttonTextColor);
        bookingsButton.addActionListener(this);

        exitButton.setBackground(buttonBackgroundColor);
        exitButton.setForeground(buttonTextColor);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 20));
        buttonPanel.setBackground(new Color(254, 249, 217));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        buttonPanel.add(roomsButton);
        buttonPanel.add(bookingsButton);
        buttonPanel.add(exitButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void addSampleRooms() {
        RoomsPage.roomList.add(new StandardRoom(101, 1, true, 100.00));
        RoomsPage.roomList.add(new DeluxeRoom(201, 2, false, 200.00));
        RoomsPage.roomList.add(new StandardRoom(102, 1, false, 100.00));
        RoomsPage.roomList.add(new DeluxeRoom(202, 2, true, 200.00));
        RoomsPage.roomList.add(new StandardRoom(103, 1, true, 100.00));
    }

    public static void main(String[] args) {
        new MainPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Rooms")) {
            new RoomsPage();
        } else if (command.equals("Bookings")) {
            new BookingsPage();
        } else if (command.equals("Exit")) {
            System.exit(0);
        }
    }  
}