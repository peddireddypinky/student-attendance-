import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LoginForm {

    JFrame frame;
    
    // Theme colors
    Color darkBg = new Color(245, 250, 255); // Light background
    Color orangeAccent = new Color(0, 102, 204); // Blue primary text/buttons
    Color lightText = new Color(30, 30, 30); // Dark text

    public LoginForm() {
        showRoleSelection();
    }

    // ================= ROLE SCREEN =================
    void showRoleSelection() {

        frame = new JFrame("Aditya University - Attendance Portal");
        frame.setSize(450,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(darkBg);

        JLabel title = new JLabel("Aditya University", JLabel.CENTER);
        title.setBounds(40,40,350,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(orangeAccent);
        
        JLabel subtitle = new JLabel("Attendance Portal | Sign In", JLabel.CENTER);
        subtitle.setBounds(40,90,350,30);
        subtitle.setFont(new Font("Segoe UI",Font.PLAIN,16));
        subtitle.setForeground(lightText);

        JButton adminBtn = new JButton("ADMIN LOGIN");
        JButton facultyBtn = new JButton("FACULTY LOGIN");
        JButton studentBtn = new JButton("STUDENT LOGIN");

        adminBtn.setBounds(100,160,230,45);
        facultyBtn.setBounds(100,220,230,45);
        studentBtn.setBounds(100,280,230,45);

        styleBtn(adminBtn);
        styleBtn(facultyBtn);
        styleBtn(studentBtn);

        frame.add(title);
        frame.add(subtitle);
        frame.add(adminBtn);
        frame.add(facultyBtn);
        frame.add(studentBtn);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0,340,450,20);
        footer.setForeground(lightText);
        frame.add(footer);

        frame.setVisible(true);

        adminBtn.addActionListener(e -> {
            frame.dispose();
            showAdminLogin();
        });
        
        facultyBtn.addActionListener(e -> {
            frame.dispose();
            showFacultyLogin();
        });

        studentBtn.addActionListener(e -> {
            frame.dispose();
            showStudentLogin();
        });
    }

    // Faculty Credentials (Course Name -> Password)
    java.util.Map<String, String> facultyCredentials = new java.util.HashMap<String, String>() {{
        put("maths", "maths");
        put("physics", "physics");
        put("java", "java");
        put("drawing", "drawing");
        put("chemistry", "chemistry");
        put("english", "english");
    }};

    // ================= FACULTY LOGIN =================
    void showFacultyLogin() {
        frame = new JFrame("Faculty Login");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(darkBg);

        JLabel u = new JLabel("Username (Course):");
        JLabel p = new JLabel("Password:");
        u.setForeground(lightText);
        p.setForeground(lightText);

        JTextField uf = new JTextField();
        JPasswordField pf = new JPasswordField();

        JButton login = new JButton("Login");
        JButton changePwd = new JButton("Change Password");

        u.setBounds(30, 60, 130, 25);
        uf.setBounds(170, 60, 150, 25);
        p.setBounds(30, 100, 130, 25);
        pf.setBounds(170, 100, 150, 25);
        login.setBounds(110, 160, 160, 30);
        changePwd.setBounds(110, 200, 160, 30);

        styleBtn(login);
        styleBtn(changePwd);

        frame.add(u); frame.add(uf);
        frame.add(p); frame.add(pf);
        frame.add(login); frame.add(changePwd);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0, 240, 400, 20);
        footer.setForeground(lightText);
        frame.add(footer);

        frame.setVisible(true);

        login.addActionListener(e -> {
            String user = uf.getText().toLowerCase();
            String pwd = new String(pf.getPassword());
            if (facultyCredentials.containsKey(user) && facultyCredentials.get(user).equals(pwd)) {
                frame.dispose();
                showFacultyPortal();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Course or Password");
            }
        });

        changePwd.addActionListener(e -> {
            String user = uf.getText().toLowerCase();
            String oldPwd = new String(pf.getPassword());
            if (facultyCredentials.containsKey(user) && facultyCredentials.get(user).equals(oldPwd)) {
                String newPwd = JOptionPane.showInputDialog(frame, "Enter new password for " + user + ":");
                if (newPwd != null && !newPwd.trim().isEmpty()) {
                    facultyCredentials.put(user, newPwd.trim());
                    JOptionPane.showMessageDialog(frame, "Password changed successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Enter valid current Username and Password first");
            }
        });
    }

    // ================= FACULTY PORTAL =================
    void showFacultyPortal() {
        frame = new JFrame("Faculty Portal");
        frame.setSize(900, 650);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(darkBg);

        // Header Title
        JLabel title = new JLabel("Aditya University | Faculty Portal");
        title.setBounds(20, 15, 400, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(orangeAccent);
        frame.add(title);

        // Separator line
        JSeparator sep = new JSeparator();
        sep.setBounds(0, 60, 900, 5);
        sep.setForeground(orangeAccent);
        frame.add(sep);

        // Filters Section
        JLabel classLbl = new JLabel("Class:");
        classLbl.setForeground(lightText);
        classLbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        classLbl.setBounds(20, 80, 50, 30);
        frame.add(classLbl);

        JComboBox<String> classCombo = new JComboBox<>(new String[]{
            "CSE | maths | Period 1 (09:30 AM - 10:30 AM)",
            "ECE | physics | Period 2 (10:30 AM - 11:30 AM)",
            "IT | java | Period 3 (11:30 AM - 12:30 PM)",
            "MECH | drawing | Period 4 (01:30 PM - 02:30 PM)",
            "CIVIL | chemistry | Period 5 (02:30 PM - 03:30 PM)",
            "EEE | english | Period 6 (03:30 PM - 04:30 PM)"
        });
        classCombo.setBounds(70, 80, 300, 30);
        frame.add(classCombo);

        JLabel dateLbl = new JLabel("Date (2026):");
        dateLbl.setForeground(lightText);
        dateLbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dateLbl.setBounds(390, 80, 100, 30);
        frame.add(dateLbl);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox<String> monthCombo = new JComboBox<>(months);
        monthCombo.setBounds(490, 80, 100, 30);
        frame.add(monthCombo);

        String[] days = new String[31];
        for(int i=1; i<=31; i++) days[i-1] = String.valueOf(i);
        JComboBox<String> dayCombo = new JComboBox<>(days);
        dayCombo.setBounds(600, 80, 50, 30);
        frame.add(dayCombo);

        JButton loadBtn = new JButton("LOAD DETAILS");
        loadBtn.setBounds(670, 80, 150, 30);
        loadBtn.setBackground(orangeAccent);
        loadBtn.setForeground(Color.WHITE);
        loadBtn.setFocusPainted(false);
        frame.add(loadBtn);

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(0, 130, 900, 5);
        sep2.setForeground(orangeAccent);
        frame.add(sep2);

        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(20, 150, 840, 350);

        // Assign Attendance Tab
        JPanel assignPanel = new JPanel();
        assignPanel.setLayout(null);
        assignPanel.setBackground(darkBg);

        // Table Model with Checkbox
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Student ID", "Student Name", "Mark Present"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) return Boolean.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.getTableHeader().setBackground(new Color(220, 230, 240));
        table.getTableHeader().setForeground(orangeAccent);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        // Load Details Action
        loadBtn.addActionListener(e -> {
            try {
                java.sql.Connection con = DBHelper.getConnection();
                if(con != null) {
                    java.sql.ResultSet rs = con.createStatement().executeQuery("SELECT roll_number, name FROM students");
                    model.setRowCount(0);
                    while(rs.next()){
                        model.addRow(new Object[]{rs.getString("roll_number"), rs.getString("name"), true});
                    }
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error loading students: " + ex.getMessage());
            }
        });

        // Custom Cell Renderer to color rows based on checkbox
        table.setDefaultRenderer(String.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean isSel, boolean hasFoc, int r, int c) {
                Component comp = super.getTableCellRendererComponent(t, v, isSel, hasFoc, r, c);
                setHorizontalAlignment(JLabel.CENTER);
                boolean isPresent = (boolean) t.getValueAt(r, 2);
                if (isPresent) {
                    comp.setBackground(new Color(180, 255, 180)); // Light green
                    comp.setForeground(new Color(0, 100, 0));
                } else {
                    comp.setBackground(new Color(255, 180, 180)); // Light red
                    comp.setForeground(new Color(150, 0, 0));
                }
                return comp;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 815, 300);
        scrollPane.getViewport().setBackground(Color.WHITE);
        assignPanel.add(scrollPane);

        tabbedPane.addTab("Assign Attendance", assignPanel);

        // Absentees List Tab 
        JPanel absenteesPanel = new JPanel();
        absenteesPanel.setBackground(darkBg);
        absenteesPanel.setLayout(null);

        DefaultTableModel absModel = new DefaultTableModel(new Object[]{"Student ID", "Student Name"}, 0);
        JTable absTable = new JTable(absModel);
        absTable.setRowHeight(35);
        absTable.getTableHeader().setBackground(new Color(220, 230, 240));
        absTable.getTableHeader().setForeground(orangeAccent);
        absTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        absTable.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane absScroll = new JScrollPane(absTable);
        absScroll.setBounds(10, 10, 815, 300);
        absScroll.getViewport().setBackground(Color.WHITE);
        absenteesPanel.add(absScroll);

        tabbedPane.addTab("Absentees List", absenteesPanel);

        // Update Absentees dynamically
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 1) { // 1 = Absentees Tab
                absModel.setRowCount(0);
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isPresent = (boolean) model.getValueAt(i, 2);
                    if (!isPresent) {
                        absModel.addRow(new Object[]{model.getValueAt(i, 0), model.getValueAt(i, 1)});
                    }
                }
            }
        });

        // Tab styling
        tabbedPane.setBackground(new Color(200,200,200));
        tabbedPane.setForeground(new Color(30, 60, 65));

        frame.add(tabbedPane);

        // Save All Attendance Button
        JButton saveBtn = new JButton("SAVE ALL ATTENDANCE");
        saveBtn.setBounds(660, 510, 200, 40);
        saveBtn.setBackground(orangeAccent);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setFocusPainted(false);
        frame.add(saveBtn);

        // Return to Home Button
        JButton returnBtn = new JButton("RETURN TO HOME");
        returnBtn.setBounds(700, 560, 160, 35);
        returnBtn.setBackground(new Color(220, 220, 220));
        returnBtn.setForeground(Color.BLACK);
        returnBtn.setFocusPainted(false);
        frame.add(returnBtn);

        // Action Listeners
        returnBtn.addActionListener(e -> {
            frame.dispose();
            showRoleSelection();
        });
        
        saveBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Attendance saved successfully!");
        });

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0,580,900,20);
        footer.setForeground(lightText);
        frame.add(footer);

        frame.setVisible(true);
        
        table.getModel().addTableModelListener(e -> {
            table.repaint(); // repaints table when a checkbox is clicked to update colors
        });
    }

    // ================= ADMIN LOGIN =================
    void showAdminLogin() {

        frame = new JFrame("Admin Login");
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(darkBg);

        JLabel u = new JLabel("Username:");
        JLabel p = new JLabel("Password:");
        u.setForeground(lightText);
        p.setForeground(lightText);

        JTextField uf = new JTextField();
        JPasswordField pf = new JPasswordField();

        JButton login = new JButton("Login");

        u.setBounds(50,70,100,25);
        uf.setBounds(160,70,150,25);
        p.setBounds(50,110,100,25);
        pf.setBounds(160,110,150,25);
        login.setBounds(130,180,120,35);

        styleBtn(login);

        frame.add(u); frame.add(uf);
        frame.add(p); frame.add(pf);
        frame.add(login);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0,240,400,20);
        footer.setForeground(lightText);
        frame.add(footer);

        frame.setVisible(true);

        login.addActionListener(e -> {
            if(uf.getText().equals("admin") && new String(pf.getPassword()).equals("1234")){
                frame.dispose();
                showAdminPanel();
            } else {
                JOptionPane.showMessageDialog(frame,"Wrong Credentials");
            }
        });
    }

    // ================= STUDENT LOGIN =================
    void showStudentLogin() {

        frame = new JFrame("Student Login");
        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(darkBg);

        JLabel r = new JLabel("Roll Number:");
        r.setForeground(lightText);
        
        JTextField rf = new JTextField();
        JButton login = new JButton("Login");

        r.setBounds(50,70,120,25);
        rf.setBounds(180,70,150,25);
        login.setBounds(130,140,120,35);

        styleBtn(login);

        frame.add(r); frame.add(rf); frame.add(login);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0,190,400,20);
        footer.setForeground(lightText);
        frame.add(footer);

        frame.setVisible(true);

        login.addActionListener(e -> {
            if(rf.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame,"Enter Roll Number");
            } else {
                frame.dispose();
                showStudentView(rf.getText());
            }
        });
    }

    // ================= ADMIN PANEL =================
    void showAdminPanel() {

        frame = new JFrame("Admin Dashboard");
        frame.setSize(850,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(darkBg);

        JLabel title = new JLabel("Aditya University | Admin Portal");
        title.setBounds(30,15,850,30);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(orangeAccent);
        frame.add(title);

        // ===== FIELDS =====
        JTextField name = new JTextField();
        JTextField roll = new JTextField();
        JTextField course = new JTextField();
        JTextField percent = new JTextField();

        JLabel lblName = new JLabel("Name"); lblName.setForeground(lightText); frame.add(lblName).setBounds(50,70,100,25);
        JLabel lblRoll = new JLabel("Roll"); lblRoll.setForeground(lightText); frame.add(lblRoll).setBounds(50,110,100,25);
        JLabel lblCourse = new JLabel("Course"); lblCourse.setForeground(lightText); frame.add(lblCourse).setBounds(50,150,100,25);
        JLabel lblAtt = new JLabel("Attendance"); lblAtt.setForeground(lightText); frame.add(lblAtt).setBounds(50,190,100,25);

        name.setBounds(150,70,200,30);
        roll.setBounds(150,110,200,30);
        course.setBounds(150,150,200,30);
        percent.setBounds(150,190,200,30);

        frame.add(name); frame.add(roll); frame.add(course); frame.add(percent);

        // ===== SIDE BUTTONS =====
        JButton search = new JButton("Search");
        JButton clear = new JButton("Clear");
        JButton logout = new JButton("Logout");
        JButton low = new JButton("Below 75%");
        JButton top = new JButton("Top 5");

        search.setBounds(400,70,120,35);
        clear.setBounds(400,120,120,35);
        logout.setBounds(650,15,120,35);
        logout.setBackground(new Color(220,220,220)); // Light grey for secondary buttons

        styleBtn(search);
        styleBtn(clear);
        logout.setForeground(lightText);
        logout.setFocusPainted(false);

        frame.add(search);
        frame.add(clear);
        frame.add(logout);

        // ===== TABLE =====
        JTable table = new JTable();
        table.setBackground(new Color(240,240,240));
        table.setForeground(Color.BLACK);
        table.getTableHeader().setBackground(new Color(220, 230, 240));
        table.getTableHeader().setForeground(orangeAccent);
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30,320,770,200);
        sp.getViewport().setBackground(darkBg);
        frame.add(sp);

        // ===== BOTTOM BUTTONS =====
        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton view = new JButton("View All");

        add.setBounds(50,250,120,40);
        update.setBounds(200,250,120,40);
        delete.setBounds(350,250,120,40);
        view.setBounds(500,250,120,40);
        low.setBounds(650,250,150,40);
        top.setBounds(650,200,150,40);

        styleBtn(add);
        styleBtn(update);
        styleBtn(delete);
        styleBtn(view);
        styleBtn(low);
        styleBtn(top);

        frame.add(add);
        frame.add(update);
        frame.add(delete);
        frame.add(view);
        frame.add(low);
        frame.add(top);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0,530,850,20);
        footer.setForeground(lightText);
        frame.add(footer);

        frame.setVisible(true);

        // ===== ACTIONS =====
        view.addActionListener(e -> DBHelper.loadTable(table,frame));

        add.addActionListener(e -> {
            try{
                DBHelper.addStudent(name.getText(),roll.getText(),course.getText(),
                        Double.parseDouble(percent.getText()));
                DBHelper.loadTable(table,frame);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,"Invalid Input");
            }
        });

        update.addActionListener(e -> {
            int r = DBHelper.updateStudent(name.getText(),course.getText(),
                    Double.parseDouble(percent.getText()),roll.getText());
            JOptionPane.showMessageDialog(frame,r>0?"Updated":"Not Found");
            DBHelper.loadTable(table,frame);
        });

        delete.addActionListener(e -> {
            int r = DBHelper.deleteStudent(roll.getText());
            JOptionPane.showMessageDialog(frame,r>0?"Deleted":"Not Found");
            DBHelper.loadTable(table,frame);
        });

        search.addActionListener(e -> DBHelper.searchStudent(table, roll.getText()));

        clear.addActionListener(e -> {
            name.setText("");
            roll.setText("");
            course.setText("");
            percent.setText("");
        });

        logout.addActionListener(e -> {
            frame.dispose();
            showRoleSelection();
        });

        // BELOW 75%
        low.addActionListener(e -> {
            try{
                java.sql.Connection con = DBHelper.getConnection();
                java.sql.ResultSet rs = con.createStatement()
                        .executeQuery("SELECT * FROM students WHERE attendance_percentage < 75");

                DefaultTableModel m = new DefaultTableModel(
                        new String[]{"ID","Name","Roll","Course","%"},0);

                while(rs.next()){
                    m.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("roll_number"),
                            rs.getString("course"),
                            rs.getDouble("attendance_percentage")
                    });
                }

                table.setModel(m);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });

        // TOP ATTENDANCE
        top.addActionListener(e -> {
            try{
                java.sql.Connection con = DBHelper.getConnection();
                java.sql.ResultSet rs = con.createStatement()
                        .executeQuery("SELECT * FROM students ORDER BY attendance_percentage DESC LIMIT 5");

                DefaultTableModel m = new DefaultTableModel(
                        new String[]{"ID","Name","Roll","Course","%"},0);

                while(rs.next()){
                    m.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("roll_number"),
                            rs.getString("course"),
                            rs.getDouble("attendance_percentage")
                    });
                }

                table.setModel(m);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });
    }

    // ================= STUDENT VIEW =================
    void showStudentView(String roll) {
        frame = new JFrame("Student Dashboard");
        frame.setSize(750,550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(darkBg);

        // ===== TITLE =====
        JLabel title = new JLabel("Aditya University | Student Portal");
        title.setBounds(30,15,400,30);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(orangeAccent);
        frame.add(title);

        // ===== BTNS =====
        JButton logout = new JButton("RETURN TO HOME");
        logout.setBounds(570,15,150,35);
        logout.setBackground(new Color(220,220,220));
        logout.setForeground(Color.BLACK);
        logout.setFocusPainted(false);
        frame.add(logout);

        logout.addActionListener(e -> {
            frame.dispose();
            showRoleSelection();
        });

        // Initialize values
        double percentage = 0.0;
        String course = "N/A";
        int totalDays = 100;
        int attendedDays = 0;

        // Fetch percentage
        try {
            java.sql.Connection con = DBHelper.getConnection();
            if(con != null) {
                java.sql.PreparedStatement ps = con.prepareStatement("SELECT course, attendance_percentage FROM students WHERE roll_number=?");
                ps.setString(1, roll);
                java.sql.ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    course = rs.getString("course");
                    percentage = rs.getDouble("attendance_percentage");
                    attendedDays = (int) percentage;
                } else {
                    JOptionPane.showMessageDialog(frame, "Roll Number not found in system.");
                }
            }
        } catch(Exception ex) {
            // ignore
        }

        // ===== PANELS =====
        JPanel p1 = new JPanel(null);
        p1.setBounds(30, 60, 210, 80);
        p1.setBackground(darkBg);
        p1.setBorder(BorderFactory.createLineBorder(orangeAccent, 1));
        
        JLabel l1 = new JLabel("Total Work Days", JLabel.CENTER);
        l1.setBounds(0, 10, 210, 20);
        l1.setForeground(lightText);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel v1 = new JLabel(String.valueOf(totalDays), JLabel.CENTER);
        v1.setBounds(0, 35, 210, 35);
        v1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        v1.setForeground(lightText);
        p1.add(l1); p1.add(v1);
        frame.add(p1);

        JPanel p2 = new JPanel(null);
        p2.setBounds(255, 60, 210, 80);
        p2.setBackground(darkBg);
        p2.setBorder(BorderFactory.createLineBorder(orangeAccent, 1));

        JLabel l2 = new JLabel("Days Attended", JLabel.CENTER);
        l2.setBounds(0, 10, 210, 20);
        l2.setForeground(lightText);
        l2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel v2 = new JLabel(String.valueOf(attendedDays), JLabel.CENTER);
        v2.setBounds(0, 35, 210, 35);
        v2.setFont(new Font("Segoe UI", Font.BOLD, 28));
        v2.setForeground(lightText);
        p2.add(l2); p2.add(v2);
        frame.add(p2);

        JPanel p3 = new JPanel(null);
        p3.setBounds(480, 60, 210, 80);
        p3.setBackground(darkBg);
        p3.setBorder(BorderFactory.createLineBorder(orangeAccent, 1));

        JLabel l3 = new JLabel("Overall %", JLabel.CENTER);
        l3.setBounds(0, 10, 210, 20);
        l3.setForeground(orangeAccent);
        l3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel v3 = new JLabel(percentage + "%", JLabel.CENTER);
        v3.setBounds(0, 35, 210, 35);
        v3.setFont(new Font("Segoe UI", Font.BOLD, 28));
        if(percentage >= 75) {
            v3.setForeground(new Color(0, 150, 0)); // green
        } else {
            v3.setForeground(new Color(200, 0, 0)); // red
        }
        p3.add(l3); p3.add(v3);
        frame.add(p3);

        // ===== LABEL FOR TOP/BELOW =====
        JLabel statusLbl = new JLabel("", JLabel.CENTER);
        statusLbl.setBounds(30, 145, 660, 25);
        statusLbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        if(percentage < 75) {
            statusLbl.setText("Note: Below Attendance (" + percentage + "%). Course: " + course);
            statusLbl.setForeground(Color.RED);
        } else if(percentage >= 90) {
            statusLbl.setText("Note: Top Student Performance! Course: " + course);
            statusLbl.setForeground(new Color(0,150,0));
        } else {
            statusLbl.setText("Course Enrolled: " + course);
            statusLbl.setForeground(lightText);
        }
        frame.add(statusLbl);

        // ===== TABLE =====
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Subject", "Period", "Status"}, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 230, 240));
        table.getTableHeader().setForeground(orangeAccent);

        // Add today's subject info
        model.addRow(new Object[]{course, "Period 1 (09:30 AM - 10:30 AM)", "Present"});
        model.addRow(new Object[]{course, "Period 2 (10:30 AM - 11:30 AM)", (percentage<75 ? "Absent" : "Present")});

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 175, 660, 250);
        sp.getViewport().setBackground(Color.WHITE);
        sp.setBorder(BorderFactory.createTitledBorder("Today's Attendance Status"));
        frame.add(sp);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0,480,750,20);
        footer.setForeground(lightText);
        frame.add(footer);

        frame.setVisible(true);
    }

    // ================= BUTTON STYLE =================
    void styleBtn(JButton b){
        b.setBackground(darkBg);
        b.setForeground(orangeAccent);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setBorder(BorderFactory.createLineBorder(orangeAccent, 1));
        b.setFocusPainted(false);
        
        // Hover effects basic
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(orangeAccent);
                b.setForeground(darkBg);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(darkBg);
                b.setForeground(orangeAccent);
            }
        });
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}