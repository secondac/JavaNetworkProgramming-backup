package assignment2;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * 특정한 목록을 관리(검색, 수정, 추가, 삭제)하는 프로그램
 *
 * 필수 항목
 *
 * 객체스트림, 파일스트림을 사용하여 목록을 파일에 저장.
 * 사용자 입력으로 객체를 추가하는 기능
 * 객체 개수는 20개 이상
 * 객체의 내용엔 제한이 없지만, 최소한의 현실성 반영 필요
 *
 * 기타 사항
 *
 * 목록의 자료구조, 관리기능의 구현방식은 자유
 * 단일클래스일 필요없음
 *
 * **/
public class Main extends JFrame {
    JPanel searchPanel;
    JPanel bPanel;
    JList bookJList;
    JLabel lCondition;
    JTextField tCondition;
    JButton searchButton;
    // JComboBox comboBox;
    JTable bookTable;
    JButton updateButton, deleteButton, addButton;
    Object[][] emp = new Object[0][5];


    /**
     * @삭제/수정/추가-확인 : Test 클래스 실행
     *
     *
     * @sendToFile :  파일로 데이터 보냄
     * @sendToFile :  ObjectOutputStream 에 FileOutputStream 연결해서 파일로 보냄
     * @ReceiveFromFile :  파일로부터 데이터 받음
     * @ReceiveFromFile :  ObjectInputStream 에 FileInputStream 연결해서 데이터 받음
     * **/

    FileInputStream fis = null;
    ObjectInputStream ois = null;
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    ArrayList<Book> list = new ArrayList<>();

    // Constructor
    public Main(){
        Book book1 = new Book(9780321714114L, "C++ Primer", 5, "Stanley B. Lippman", 2012, 41.82 );
        Book book2 = new Book(9781449357672L, "Java Network Programming",4, "Elliotte Rusty Harold", 2013, 26.49 );
        Book book3 = new Book(9780321334879L, "Effective C++",3, "Scott Meyers", 2005, 43.73 );
        Book book4 = new Book(9789390727506L,"Database system concepts",7," Henry Korth, Abraham Silberschatz", 2021, 53.63);
        Book book5 = new Book(9780672337475L, "OpenGL Superbible", 7, "Graham Sellers, Richard Wright Jr., Nicholas Haemel",2015, 43.49);
        Book book6 = new Book(9781617294945L, "Spring in Action", 6, "Craig Walls", 2018, 44.99 );
        Book book7 = new Book(9781617291470L, "Netty in Action", 1, "Norman Maurer, Marvin Allen Wolfthal", 2016, 37.99 );
        Book book8 = new Book(9780134997834L, "A Tour of C++", 3, "Bjarne Stroustrup", 2018, 35.99 );
        Book book9 = new Book(9780534950972L, "Introduction to the Theory of Computation", 3, "Michael Sipser", 2012, 90.00);
        Book book10 = new Book(9781617298254L, "Troubleshooting Java", 1, "Lauren Malhoit, Andy Syrewicze", 2023, 42.99);
        Book book11 = new Book(9780262033848L, "Introduction to Algorithms", 3, "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", 2009, 99.95);
        Book book12 = new Book(9781119695401L, "Professional C++", 5, "Marc Gregoire", 2021, 60.00);

        Book[] books = {book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12};

        for (Book book : books) {
            list.add(book);
        }

        sendToFile();
        receiveFromFile();

        initTableModel();
        setTitle("Book Data");
        setLayout(new BorderLayout());
        Container con = getContentPane();
        con.add(searchPanel, BorderLayout.NORTH);
        con.add(new JScrollPane(bookTable), BorderLayout.CENTER);
        con.add(bPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(256,256);
        pack();
        setSize(768,512);
        setVisible(true);

    }


    public void sendToFile(){
        try{
            fos = new FileOutputStream("books.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            System.out.println("저장 완료");

        }catch (Exception ex){}
        finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void receiveFromFile(){
        try{
            fis = new FileInputStream("books.dat");
            ois = new ObjectInputStream(fis);

            ArrayList<Book> list = (ArrayList<Book>) ois.readObject();

            int i = 0;
            for (Book b : list) {
                b = (Book)list.get(i);
                System.out.println("b"+i+".toString() : " + b.toString());
                i++;
            }


        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                fis.close();

            } catch (IOException ex){
                ex.printStackTrace();
            }

        }
    }



    private void initTableModel() {
        searchPanel = new JPanel();
        tCondition = new JTextField(10);
        lCondition = new JLabel("Search Title");
        bookJList = new JList();
        bookJList.setBackground(Color.WHITE);
        bookTable = new JTable();

        // 테이블에 표시할 데이터를 ArrayList에서 가져옴
        String[] columnNames = { "ISBN", "Title", "Edition", "Author", "Year", "Price" };
        Object[][] data = new Object[list.size()][6];  // Book 데이터 크기만큼 배열을 만듦

        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            data[i][0] = book.getIsbn();   // ISBN
            data[i][1] = book.getTitle();  // Title
            data[i][2] = book.getEdition(); // Edition
            data[i][3] = book.getAuthor(); // Author
            data[i][4] = book.getYear();   // Year
            data[i][5] = book.getPrice();  // Price
        }

        // 테이블에 데이터를 표시하기 위해 DefaultTableModel 사용
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        bookTable.setModel(tableModel);  // JTable에 모델 설정

        // 검색 버튼 설정
        searchButton = new JButton("조회");
        searchButton.addActionListener(e -> {
            String searchText = tCondition.getText().toLowerCase();
            ArrayList<Book> filteredList = new ArrayList<>();

            // 검색 조건에 맞는 책만 필터링
            for (Book book : list) {
                if (book.getTitle().toLowerCase().contains(searchText) ||
                        book.getAuthor().toLowerCase().contains(searchText)) {
                    filteredList.add(book);
                }
            }

            updateTableData(filteredList);
        });

        // JPanel에 추가하거나 JFrame에 추가
        searchPanel.add(lCondition);
        searchPanel.add(tCondition);
        searchPanel.add(searchButton);
        bPanel = new JPanel();

        updateButton = new JButton("수정하기");
        updateButton.addActionListener(e -> {
            int selectedRow = bookTable.getSelectedRow();  // 선택된 행의 인덱스 가져오기
            if (selectedRow != -1) {
                // 선택된 행의 Book 객체 가져오기
                Book selectedBook = list.get(selectedRow);

                // 입력 필드에 기존 데이터 채워 넣기
                JTextField isbnField = new JTextField(Long.toString(selectedBook.getIsbn()));  // 수정할 경우에는 ISBN도 포함
                JTextField titleField = new JTextField(selectedBook.getTitle());
                JTextField authorField = new JTextField(selectedBook.getAuthor());
                JTextField editionField = new JTextField(Integer.toString(selectedBook.getEdition()));
                JTextField yearField = new JTextField(Integer.toString(selectedBook.getYear()));
                JTextField priceField = new JTextField(Double.toString(selectedBook.getPrice()));

                // 입력 패널 생성
                JPanel panel = createBookInputPanel(isbnField, titleField, authorField, editionField, yearField, priceField);

                // 다이얼로그 표시
                int result = JOptionPane.showConfirmDialog(null, panel, "책 정보 수정", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                // 확인 버튼이 눌렸을 때 수정 처리
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // 필드 값 업데이트
                        selectedBook.setIsbn(Long.parseLong(isbnField.getText()));  // ISBN도 수정 가능하게
                        selectedBook.setTitle(titleField.getText());
                        selectedBook.setAuthor(authorField.getText());
                        selectedBook.setEdition(Integer.parseInt(editionField.getText()));
                        selectedBook.setYear(Integer.parseInt(yearField.getText()));
                        selectedBook.setPrice(Double.parseDouble(priceField.getText()));

                        // 수정된 데이터를 테이블과 파일에 반영
                        updateTableData(list);
                        sendToFile();  // 수정된 내용을 파일에 저장
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "입력 형식이 잘못되었습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "수정할 항목을 선택하세요.");
            }
        });

        deleteButton = new JButton("삭제하기");
        deleteButton.addActionListener(e -> {
            int selectedRow = bookTable.getSelectedRow();  // 선택된 행의 인덱스 가져오기
            if (selectedRow != -1) {
                // 선택된 행에 해당하는 Book 객체를 리스트에서 삭제
                list.remove(selectedRow);
                updateTableData(list);  // 테이블 데이터 갱신
                sendToFile();  // 파일로 저장하여 데이터 갱신
            } else {
                JOptionPane.showMessageDialog(this, "삭제할 항목을 선택하세요.");
            }
        });

        addButton = new JButton("추가하기");
        addButton.addActionListener(e -> {
            JTextField isbnField = new JTextField();
            JTextField titleField = new JTextField();
            JTextField authorField = new JTextField();
            JTextField editionField = new JTextField();
            JTextField yearField = new JTextField();
            JTextField priceField = new JTextField();

            // 입력 패널 생성
            JPanel panel = createBookInputPanel(isbnField, titleField, authorField, editionField, yearField, priceField);

            // 다이얼로그 표시
            int result = JOptionPane.showConfirmDialog(null, panel, "새 책 추가", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // 확인 버튼이 눌렸을 때 처리
            if (result == JOptionPane.OK_OPTION) {
                try {
                    // 입력 값이 비어있지 않은지 확인
                    if (isbnField.getText().isEmpty() || titleField.getText().isEmpty() || authorField.getText().isEmpty() ||
                            editionField.getText().isEmpty() || yearField.getText().isEmpty() || priceField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // 새로운 Book 객체 생성
                    Book newBook = new Book(
                            Long.parseLong(isbnField.getText()),
                            titleField.getText(),
                            Integer.parseInt(editionField.getText()),
                            authorField.getText(),
                            Integer.parseInt(yearField.getText()),
                            Double.parseDouble(priceField.getText())
                    );

                    // 리스트에 추가 및 테이블 갱신
                    list.add(newBook);
                    updateTableData(list);
                    sendToFile();  // 파일로 저장
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "입력 형식이 잘못되었습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bPanel.add(addButton);
        bPanel.add(updateButton);
        bPanel.add(deleteButton);
    }


    private JPanel createBookInputPanel(JTextField isbnField, JTextField titleField, JTextField authorField,
                                        JTextField editionField, JTextField yearField, JTextField priceField) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Edition:"));
        panel.add(editionField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        return panel;
    }


    private void updateTableData(ArrayList<Book> bookList) {
        String[] columnNames = { "ISBN", "Title", "Edition", "Author", "Year", "Price" };
        Object[][] data = new Object[bookList.size()][6];  // Book 데이터 크기만큼 배열을 만듦

        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            data[i][0] = book.getIsbn();   // ISBN
            data[i][1] = book.getTitle();  // Title
            data[i][2] = book.getEdition(); // Edition
            data[i][3] = book.getAuthor(); // Author
            data[i][4] = book.getYear();   // Year
            data[i][5] = book.getPrice();  // Price
        }

        // 테이블에 데이터를 표시하기 위해 DefaultTableModel 사용
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        bookTable.setModel(tableModel);  // JTable에 모델 설정
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}

/**
 *
 *             Book b1 = (Book)list.get(0);
 *             Book b2 = (Book)list.get(1);
 *             Book b3 = (Book)list.get(2);
 *             System.out.println("b1.toString() : " + b1.toString());
 *             System.out.println("b2.toString() : " + b2.toString());
 *             System.out.println("b3.toString() : " + b3.toString());
 *             **/