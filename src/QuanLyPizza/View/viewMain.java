/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QuanLyPizza.View;

import QuanLyPizza.Class.CHITIETHOADON;
import QuanLyPizza.Class.HOADON;
import QuanLyPizza.Class.KHACHHANG;
import QuanLyPizza.Class.KHUYENMAI;
import QuanLyPizza.Class.LOAI;
import QuanLyPizza.Class.NHACUNGCAP;
import QuanLyPizza.Class.NHANVIEN;
import QuanLyPizza.Class.PHIEUNHAP;
import QuanLyPizza.Class.SANPHAM;
import QuanLyPizza.Class.TOPTHONGKE;
import static QuanLyPizza.DAO.ChiTietHoaDonDAO.limitTOP;
import static QuanLyPizza.DAO.ChiTietHoaDonDAO.showChiTietHoaDon;
import static QuanLyPizza.DAO.HoaDonDAO.coutDoanhThu;
import static QuanLyPizza.DAO.HoaDonDAO.coutDoanhThuChart;
import static QuanLyPizza.DAO.HoaDonDAO.showHoaDon;
import static QuanLyPizza.DAO.KhachHangDAO.addKH;
import static QuanLyPizza.DAO.KhachHangDAO.coutKH;
import static QuanLyPizza.DAO.KhachHangDAO.deleteKH;
import static QuanLyPizza.DAO.KhachHangDAO.getAllKH;
import static QuanLyPizza.DAO.KhachHangDAO.searchKH;
import static QuanLyPizza.DAO.KhachHangDAO.searchKHChiTieu;
import static QuanLyPizza.DAO.KhachHangDAO.updateKH;
import static QuanLyPizza.DAO.KhuyenMaiDAO.addKM;
import static QuanLyPizza.DAO.KhuyenMaiDAO.deleteKM;
import static QuanLyPizza.DAO.KhuyenMaiDAO.getAllKM;
import static QuanLyPizza.DAO.KhuyenMaiDAO.updateKM;
import static QuanLyPizza.DAO.LoaiDAO.getAllLoai;
import static QuanLyPizza.DAO.NhaCungCapDAO.getAllNCC;
import static QuanLyPizza.DAO.NhanVienDAO.addNV;
import static QuanLyPizza.DAO.NhanVienDAO.coutNV;
import static QuanLyPizza.DAO.NhanVienDAO.deleteNV;
import static QuanLyPizza.DAO.NhanVienDAO.exportExelNV;
import static QuanLyPizza.DAO.NhanVienDAO.getAllNV;
import static QuanLyPizza.DAO.NhanVienDAO.importExelNV;
import static QuanLyPizza.DAO.NhanVienDAO.searchNV;
import static QuanLyPizza.DAO.NhanVienDAO.updateNV;
import static QuanLyPizza.DAO.PhieuNhapDAO.addPN;
import static QuanLyPizza.DAO.PhieuNhapDAO.count;
import static QuanLyPizza.DAO.PhieuNhapDAO.deletePN;
import static QuanLyPizza.DAO.PhieuNhapDAO.getAllPN;
import static QuanLyPizza.DAO.PhieuNhapDAO.getAllPNDist;
import static QuanLyPizza.DAO.PhieuNhapDAO.getAllPNDistSearch;
import static QuanLyPizza.DAO.PhieuNhapDAO.getAllPNKey;
import static QuanLyPizza.DAO.PhieuNhapDAO.updatePN;
import static QuanLyPizza.DAO.SanPhamDAO.addSP;
import static QuanLyPizza.DAO.SanPhamDAO.coutSP;
import static QuanLyPizza.DAO.SanPhamDAO.deleteSP;
import static QuanLyPizza.DAO.SanPhamDAO.exportExel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static QuanLyPizza.DAO.SanPhamDAO.getAllSP;
import static QuanLyPizza.DAO.SanPhamDAO.getSPKey;
import static QuanLyPizza.DAO.SanPhamDAO.importExel;
import static QuanLyPizza.DAO.SanPhamDAO.searchProduct;
import static QuanLyPizza.DAO.SanPhamDAO.updateSP;
import QuanLyPizza.ravenchart.ModelChart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author HUU77
 */
public class viewMain extends javax.swing.JFrame {

    Color defaucolor, clickColor, yellow, white;

    // hai list toàn cục để đưa sản phẩm vào giỏ hàng    
    public static List<SANPHAM> listCOPPY = new ArrayList<>();
    public List<String> l = new ArrayList<>();
    ArrayList<PHIEUNHAP> listNH = new ArrayList<>();
    String MaPN = "pn" + String.valueOf(count());
    //list này dùng để chứ các sp đã đc tìm kiếm 
    List<SANPHAM> listSearch = new ArrayList<>();
    List<NHANVIEN> listSearchNV = new ArrayList<>();
    List<SANPHAM> listSearch1 = new ArrayList<>();
    ArrayList<PHIEUNHAP> listPNSearch = new ArrayList<>();
    List<KHACHHANG> listSeachKH = new ArrayList<>();
    List<KHACHHANG> listSeachKH1 = new ArrayList<>();
    String keySearchKH;
    double min;
    double max;

    public viewMain() throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        defaucolor = new Color(255, 204, 0);
        clickColor = new Color(255, 204, 0);
        yellow = new Color(255, 140, 0);
        white = new Color(255, 255, 255);
        //getConnection();

        this.showDATA();
        this.fillTableForFormProduct();
        this.fillComboboxProduct();
        this.fillComboboxSale();
        this.fillTableForFormNV();
        this.fillTableKhoHang();
        this.fillComboboxKhoHang();
        this.fillTableCMaPN();
        this.fillTableForFormKM();
        this.fillTableForFormKH();
        this.showChart();
        this.showDataHoaDon();
        this.showCoutThongKe();
        this.showTOpBANCHAY();
        buttom_save.setEnabled(false);
        buttom_clear.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        buttom_saveNV.setEnabled(false);
        btnSetup.setEnabled(false);
        btnXoaNhap.setEnabled(false);
        btnXoaPN.setEnabled(false);
        btnSuaPN.setEnabled(false);
        this.fillComboboxSaleNV();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu12 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        jMenuBar7 = new javax.swing.JMenuBar();
        jMenu16 = new javax.swing.JMenu();
        jMenu17 = new javax.swing.JMenu();
        jMenu18 = new javax.swing.JMenu();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panleMain = new javax.swing.JPanel();
        p1 = new javax.swing.JLabel();
        p2 = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();
        P4 = new javax.swing.JLabel();
        P5 = new javax.swing.JLabel();
        P6 = new javax.swing.JLabel();
        P7 = new javax.swing.JLabel();
        P8 = new javax.swing.JLabel();
        tab3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        m1 = new javax.swing.JPanel();
        tab_MENU1 = new javax.swing.JTabbedPane();
        tab_banHang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSP = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGH = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboboxSP = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        spinerSP = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        btnAddBag = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonDelSale = new javax.swing.JButton();
        exportBill = new javax.swing.JButton();
        ComboxNhanVien = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        area = new javax.swing.JScrollPane();
        tbDSHDCHIL = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbCTHD = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        txtTENSP = new javax.swing.JTextField();
        txtSOLUONG = new javax.swing.JTextField();
        txtDOnGia = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lableMHD = new javax.swing.JLabel();
        lableMKH = new javax.swing.JLabel();
        labletongtien = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        fieldTien = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        m3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        txtDieuKien = new javax.swing.JTextField();
        txtTinhTrangKM = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        Date1 = new javax.swing.JTextField();
        Date2 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableMaKM = new javax.swing.JTable();
        btnLoading = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtPhanTramKM = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btnThem1 = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        SanPham = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        fieldSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSP_formSP = new javax.swing.JTable();
        buttom_them = new javax.swing.JButton();
        buttom_save = new javax.swing.JButton();
        buttom_clear = new javax.swing.JButton();
        button_search = new javax.swing.JButton();
        button_export = new javax.swing.JButton();
        button_import = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        fieldMaSP = new javax.swing.JTextField();
        fieldTenSP = new javax.swing.JTextField();
        fieldSoLuong = new javax.swing.JTextField();
        fieldDonViTinh = new javax.swing.JTextField();
        fieldDonGia = new javax.swing.JTextField();
        comboBoxLoai = new javax.swing.JComboBox<>();
        button_refresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        m4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        fieldMaNV = new javax.swing.JTextField();
        fieldTenNV = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        fieldSex = new javax.swing.JTextField();
        fieldNamSinh = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        fieldSearchNV = new javax.swing.JTextField();
        buttom_themNV = new javax.swing.JButton();
        buttom_saveNV = new javax.swing.JButton();
        buttom_clearNV = new javax.swing.JButton();
        button_exportNV = new javax.swing.JButton();
        button_importNV = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableNV_formNV = new javax.swing.JTable();
        button_refresh1 = new javax.swing.JButton();
        btnSetup = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        m7 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        btnFind = new javax.swing.JButton();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtTongChiTieu = new javax.swing.JTextField();
        txtTuKhoaTim = new javax.swing.JTextField();
        txtMin = new javax.swing.JTextField();
        txtMax = new javax.swing.JTextField();
        cbxGioiTinh = new javax.swing.JComboBox<>();
        btnFind1 = new javax.swing.JButton();
        btnLoadingKH = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        m6 = new javax.swing.JPanel();
        tab_MENU2 = new javax.swing.JTabbedPane();
        paneNhapHang = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKhoHang = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        fieldGiaForNhapHang = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnNhapHang = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        btnXoaNhap = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        fieldSearchKhoHang = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        btnSearchKhoHang = new javax.swing.JButton();
        fieldSoLuongForNhapHang = new javax.swing.JTextField();
        fieldNCCForNhapHang = new javax.swing.JComboBox<>();
        fieldMaSPForNhapHang = new javax.swing.JLabel();
        fieldTenSPForNhapHang = new javax.swing.JLabel();
        button_refresh3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblCTPN = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        fieldSearchCTPN = new javax.swing.JTextField();
        btnSearchCTPH = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        maPN_CTPN = new javax.swing.JLabel();
        ngaynhap_CTPN = new javax.swing.JLabel();
        tongTien_CTPN = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        fieldMaNCCForPN = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        fieldGiaForPN = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        fieldMaSPForPN = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        fieldSoLuongForPN = new javax.swing.JTextField();
        btnXoaPN = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSuaPN = new javax.swing.JButton();
        button_refresh2 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblMaPN = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtCoutMONAN = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        txtCoutKH = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        txtCoutNV = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        txtCoutDoanhThu = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbTOPBAnCHAY = new javax.swing.JTable();
        jLabel76 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        chart = new QuanLyPizza.ravenchart.Chart();
        jLabel78 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("jMenu9");

        jMenu10.setText("File");
        jMenuBar5.add(jMenu10);

        jMenu11.setText("Edit");
        jMenuBar5.add(jMenu11);

        jMenu12.setText("File");
        jMenuBar6.add(jMenu12);

        jMenu13.setText("Edit");
        jMenuBar6.add(jMenu13);

        jMenu14.setText("jMenu14");

        jMenu15.setText("jMenu15");

        jMenu16.setText("File");
        jMenuBar7.add(jMenu16);

        jMenu17.setText("Edit");
        jMenuBar7.add(jMenu17);

        jMenu18.setText("jMenu18");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pizza-logo-free-vector.jpg"))); // NOI18N
        jLabel1.setText(" ");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 120));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 130));

        panleMain.setBackground(new java.awt.Color(55, 55, 55));

        p1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        p1.setForeground(new java.awt.Color(255, 255, 255));
        p1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/luggage-trolley.png"))); // NOI18N
        p1.setText("BÁN HÀNG");
        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p1MouseReleased(evt);
            }
        });

        p2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        p2.setForeground(new java.awt.Color(255, 255, 255));
        p2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/youtube-advertising.png"))); // NOI18N
        p2.setText("KHUYẾN MÃI");
        p2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p2MouseReleased(evt);
            }
        });

        p3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        p3.setForeground(new java.awt.Color(255, 255, 255));
        p3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/pizza.png"))); // NOI18N
        p3.setText("   SẢN PHẨM");
        p3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p3MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                p3MouseReleased(evt);
            }
        });

        P4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        P4.setForeground(new java.awt.Color(255, 255, 255));
        P4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/people-carry.png"))); // NOI18N
        P4.setText("NHÂN VIÊN");
        P4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P4MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                P4MouseReleased(evt);
            }
        });

        P5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        P5.setForeground(new java.awt.Color(255, 255, 255));
        P5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/bank-card-front-side.png"))); // NOI18N
        P5.setText("   KHÁCH HÀNG");
        P5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P5MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                P5MouseReleased(evt);
            }
        });

        P6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        P6.setForeground(new java.awt.Color(255, 255, 255));
        P6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/combo-chart.png"))); // NOI18N
        P6.setText("   CHART");
        P6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P6MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                P6MouseReleased(evt);
            }
        });

        P7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        P7.setForeground(new java.awt.Color(255, 255, 255));
        P7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/external-exit-essentials-tanah-basah-basic-outline-tanah-basah-2.png"))); // NOI18N
        P7.setText("   EXIT");
        P7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P7MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                P7MouseReleased(evt);
            }
        });

        P8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        P8.setForeground(new java.awt.Color(255, 255, 255));
        P8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/people-in-car.png"))); // NOI18N
        P8.setText("NHẬP HÀNG");
        P8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P8MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                P8MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panleMainLayout = new javax.swing.GroupLayout(panleMain);
        panleMain.setLayout(panleMainLayout);
        panleMainLayout.setHorizontalGroup(
            panleMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(P4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(P5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panleMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(P8, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panleMainLayout.createSequentialGroup()
                .addGroup(panleMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panleMainLayout.setVerticalGroup(
            panleMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panleMainLayout.createSequentialGroup()
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P8, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel3.add(panleMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 126, 200, 590));

        m1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tab_MENU1.setBackground(new java.awt.Color(255, 255, 255));
        tab_MENU1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_MENU1MouseClicked(evt);
            }
        });

        tab_banHang.setPreferredSize(new java.awt.Dimension(1200, 557));
        tab_banHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_banHangMouseClicked(evt);
            }
        });

        tbSP.setBackground(new java.awt.Color(204, 204, 204));
        tbSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                " LOẠI SP", "MÃ SP", "TÊN SP", "ĐƠN GIÁ", " SỐ LƯƠNG"
            }
        ));
        tbSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSP);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setText("GIỎ HÀNG");

        tbGH.setBackground(new java.awt.Color(204, 204, 204));
        tbGH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ SP", "TÊN SP ", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbGH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbGH);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setText("CHI TIET SAN PHAM");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("LOAI SAN PHAM");

        comboboxSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0 - CHON LOAI" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("MÃ SẢN PHẨM");

        txtMaSP.setText(" ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("TÊN SẢN PHẨM");

        txtTenSP.setText(" ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("ĐƠN GIÁ");

        txtDonGia.setText(" ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("SỐ LƯỢNG");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("NHÂN VIÊN ");

        btnAddBag.setBackground(new java.awt.Color(255, 102, 0));
        btnAddBag.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnAddBag.setForeground(new java.awt.Color(255, 255, 255));
        btnAddBag.setText("THÊM VÀO GIỎ");
        btnAddBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBagActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user-skin-type-7.png"))); // NOI18N
        jLabel11.setText(" ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setText("DANH SÁCH SẢN PHẨM");

        buttonDelSale.setBackground(new java.awt.Color(255, 102, 0));
        buttonDelSale.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        buttonDelSale.setForeground(new java.awt.Color(255, 255, 255));
        buttonDelSale.setText("XÓA");
        buttonDelSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDelSaleActionPerformed(evt);
            }
        });

        exportBill.setBackground(new java.awt.Color(255, 102, 0));
        exportBill.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        exportBill.setForeground(new java.awt.Color(255, 255, 255));
        exportBill.setText("XUẤT HÓA ĐƠN");
        exportBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportBillMouseClicked(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel79.setText("RESET");
        jLabel79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel79MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab_banHangLayout = new javax.swing.GroupLayout(tab_banHang);
        tab_banHang.setLayout(tab_banHangLayout);
        tab_banHangLayout.setHorizontalGroup(
            tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_banHangLayout.createSequentialGroup()
                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_banHangLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab_banHangLayout.createSequentialGroup()
                                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab_banHangLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab_banHangLayout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tab_banHangLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(tab_banHangLayout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboboxSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_banHangLayout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTenSP))
                                            .addGroup(tab_banHangLayout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(tab_banHangLayout.createSequentialGroup()
                                                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(33, 33, 33)
                                                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtDonGia)
                                                    .addComponent(spinerSP)))))))
                            .addGroup(tab_banHangLayout.createSequentialGroup()
                                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab_banHangLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnAddBag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ComboxNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(tab_banHangLayout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addComponent(buttonDelSale)
                                        .addGap(45, 45, 45)
                                        .addComponent(exportBill))
                                    .addGroup(tab_banHangLayout.createSequentialGroup()
                                        .addGap(170, 170, 170)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(tab_banHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(241, Short.MAX_VALUE))
        );
        tab_banHangLayout.setVerticalGroup(
            tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_banHangLayout.createSequentialGroup()
                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_banHangLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab_banHangLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4)
                        .addGap(25, 25, 25)
                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinerSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_banHangLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab_banHangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddBag)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab_banHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonDelSale)
                            .addComponent(exportBill))))
                .addContainerGap())
        );

        tab_MENU1.addTab("BÁN HÀNG", tab_banHang);

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("DANH SÁCH HÓA ĐƠN");

        tbDSHDCHIL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDSHDCHIL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDSHDCHILMouseClicked(evt);
            }
        });
        area.setViewportView(tbDSHDCHIL);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("CHI TIẾT HÓA ĐƠN");

        tbCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Mã SP", "Số Lượng", "Giá", "Thành Tiền"
            }
        ));
        tbCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTHDMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbCTHD);

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel59.setText("MÃ HÓA ĐƠN");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel60.setText("SẢN PHẨM");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel61.setText("SỐ LƯỢNG");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel62.setText("ĐƠN GIÁ ");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setText("TỔNG TIỀN");

        txtTENSP.setText(" ");
        txtTENSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTENSPActionPerformed(evt);
            }
        });

        txtSOLUONG.setText(" ");
        txtSOLUONG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSOLUONGActionPerformed(evt);
            }
        });

        txtDOnGia.setText(" ");
        txtDOnGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDOnGiaActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel64.setText("RESET");
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("MÃ KH");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel65.setText("THÀNH TIỀN");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel61)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSOLUONG))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel62)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDOnGia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel59))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(lableMHD)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25)))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel65)
                                    .addComponent(jLabel60))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTENSP, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldTien, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lableMKH)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labletongtien)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jLabel25)
                    .addComponent(lableMHD)
                    .addComponent(lableMKH)
                    .addComponent(jLabel63)
                    .addComponent(labletongtien))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(txtDOnGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(txtTENSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(txtSOLUONG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(fieldTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab_MENU1.addTab("HÓA ĐƠN", jPanel11);

        m1.add(tab_MENU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 22, 1040, 590));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab3.addTab("tab1", jPanel2);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete-icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        Date2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Date2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnXoa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Date1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                        .addComponent(Date2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTinhTrangKM, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(137, 137, 137))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtTinhTrangKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel48.setText("QUẢN LÝ MÃ KHUYẾN MÃI");

        tableMaKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khuyến mãi", "Chương trình", "Phần Trăm KM", "Điều kiện", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"
            }
        ));
        tableMaKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMaKMMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tableMaKM);

        btnLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Refresh-icon.png"))); // NOI18N
        btnLoading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadingActionPerformed(evt);
            }
        });

        jLabel41.setText("Mã Khuyến Mãi");

        txtMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKMActionPerformed(evt);
            }
        });

        jLabel42.setText("Tên Chương trình");

        jLabel43.setText("Phần trăm giảm");

        jLabel44.setText("Điều kiện (>x)");

        jLabel45.setText("Ngày bắt đầu");

        jLabel46.setText("Ngày kết thúc");

        jLabel47.setText("Tình Trạng");

        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-icon.png"))); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Pencil-icon.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout m3Layout = new javax.swing.GroupLayout(m3);
        m3.setLayout(m3Layout);
        m3Layout.setHorizontalGroup(
            m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m3Layout.createSequentialGroup()
                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(m3Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoading))
                    .addGroup(m3Layout.createSequentialGroup()
                        .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThem1)
                                .addGap(107, 107, 107)
                                .addComponent(btnSua)
                                .addGap(47, 47, 47))
                            .addGroup(m3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel42))
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)
                                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhanTramKM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTenKM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                        .addComponent(txtMaKM, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(44, 44, 44)
                                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(m3Layout.createSequentialGroup()
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(m3Layout.createSequentialGroup()
                                        .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel44)
                                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)))))
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9))
                .addContainerGap())
        );
        m3Layout.setVerticalGroup(
            m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(m3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(m3Layout.createSequentialGroup()
                        .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(m3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44))
                                .addGap(18, 18, 18)
                                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel45))
                                .addGap(18, 18, 18)
                                .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPhanTramKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel46)))
                            .addGroup(m3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel41)))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(m3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab3.addTab("tab2", jPanel5);

        jLabel12.setText("Mã SP");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 25)); // NOI18N
        jLabel13.setText("QUẢN LÝ SẢN PHẨM");

        jLabel14.setText("Tên SP");

        jLabel16.setText("Số lượng");

        jLabel17.setText("Đơn vị tính");

        jLabel18.setText("Đơn giá");

        fieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldSearchActionPerformed(evt);
            }
        });

        tableSP_formSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Loại", "Số lượng", "Đơn giá", "Đơn vị tính"
            }
        ));
        tableSP_formSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSP_formSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableSP_formSP);

        buttom_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-icon.png"))); // NOI18N
        buttom_them.setText("Thêm");
        buttom_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttom_themActionPerformed(evt);
            }
        });

        buttom_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Pencil-icon.png"))); // NOI18N
        buttom_save.setText("Sửa");
        buttom_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttom_saveActionPerformed(evt);
            }
        });

        buttom_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete-icon.png"))); // NOI18N
        buttom_clear.setText("Xoá");
        buttom_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttom_clearActionPerformed(evt);
            }
        });

        button_search.setText("Tìm kiếm");
        button_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_searchActionPerformed(evt);
            }
        });

        button_export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/excel-icon.png"))); // NOI18N
        button_export.setText("Xuất");
        button_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_exportActionPerformed(evt);
            }
        });

        button_import.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/excel-icon.png"))); // NOI18N
        button_import.setText("Nhập");
        button_import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_importActionPerformed(evt);
            }
        });

        jLabel20.setText("Loại");

        fieldTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTenSPActionPerformed(evt);
            }
        });

        fieldSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldSoLuongActionPerformed(evt);
            }
        });

        fieldDonViTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDonViTinhActionPerformed(evt);
            }
        });

        fieldDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDonGiaActionPerformed(evt);
            }
        });

        button_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Refresh-icon.png"))); // NOI18N
        button_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SanPhamLayout = new javax.swing.GroupLayout(SanPham);
        SanPham.setLayout(SanPhamLayout);
        SanPhamLayout.setHorizontalGroup(
            SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addGap(67, 67, 67))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_search)
                .addGap(18, 18, 18)
                .addComponent(fieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
            .addGroup(SanPhamLayout.createSequentialGroup()
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_refresh))
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SanPhamLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel20)))
                            .addGroup(SanPhamLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(buttom_them)))
                        .addGap(30, 30, 30)
                        .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SanPhamLayout.createSequentialGroup()
                                .addComponent(buttom_save)
                                .addGap(55, 55, 55)
                                .addComponent(buttom_clear)
                                .addGap(51, 51, 51)
                                .addComponent(button_export)
                                .addGap(68, 68, 68)
                                .addComponent(button_import))
                            .addGroup(SanPhamLayout.createSequentialGroup()
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldMaSP)
                                    .addComponent(fieldDonViTinh)
                                    .addComponent(comboBoxLoai, 0, 207, Short.MAX_VALUE))
                                .addGap(38, 38, 38)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SanPhamLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(33, 33, 33)
                                        .addComponent(fieldTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SanPhamLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fieldSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(SanPhamLayout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fieldDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        SanPhamLayout.setVerticalGroup(
            SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamLayout.createSequentialGroup()
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel13))
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(button_refresh)))
                .addGap(30, 30, 30)
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(fieldMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(fieldTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel16)
                    .addComponent(fieldSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(fieldDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(40, 40, 40)
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttom_them)
                    .addComponent(buttom_save)
                    .addComponent(buttom_clear)
                    .addComponent(button_export)
                    .addComponent(button_import))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab3.addTab("tab3", jPanel6);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel15.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel19.setText("Mã Nhân Vien");

        jLabel21.setText("Tên Nhân Viên");

        fieldTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTenNVActionPerformed(evt);
            }
        });

        jLabel22.setText("Giới Tính");

        jLabel23.setText("Năm Sinh");

        fieldNamSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNamSinhActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        buttom_themNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-icon.png"))); // NOI18N
        buttom_themNV.setText("Thêm");
        buttom_themNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttom_themNVActionPerformed(evt);
            }
        });

        buttom_saveNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Pencil-icon.png"))); // NOI18N
        buttom_saveNV.setText("Sửa");
        buttom_saveNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttom_saveNVActionPerformed(evt);
            }
        });

        buttom_clearNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete-icon.png"))); // NOI18N
        buttom_clearNV.setText("Xoá");
        buttom_clearNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttom_clearNVActionPerformed(evt);
            }
        });

        button_exportNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/excel-icon.png"))); // NOI18N
        button_exportNV.setText("Xuất");
        button_exportNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_exportNVActionPerformed(evt);
            }
        });

        button_importNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/excel-icon.png"))); // NOI18N
        button_importNV.setText("Nhập");
        button_importNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_importNVActionPerformed(evt);
            }
        });

        tableNV_formNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Giới Tính", "Ngày Sinh"
            }
        ));
        tableNV_formNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNV_formNVMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableNV_formNV);

        button_refresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Refresh-icon.png"))); // NOI18N
        button_refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_refresh1ActionPerformed(evt);
            }
        });

        btnSetup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/icons8_man_with_key_32px.png"))); // NOI18N
        btnSetup.setText("Tài khoản");
        btnSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout m4Layout = new javax.swing.GroupLayout(m4);
        m4.setLayout(m4Layout);
        m4Layout.setHorizontalGroup(
            m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(m4Layout.createSequentialGroup()
                        .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m4Layout.createSequentialGroup()
                                .addComponent(jScrollPane4)
                                .addGap(22, 22, 22))
                            .addGroup(m4Layout.createSequentialGroup()
                                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel21))
                                .addGap(37, 37, 37)
                                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(95, 95, 95)
                                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))
                                .addGap(46, 46, 46)
                                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldSex, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                    .addComponent(fieldNamSinh))
                                .addGap(13, 13, 13)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(m4Layout.createSequentialGroup()
                        .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(m4Layout.createSequentialGroup()
                                .addComponent(buttom_themNV)
                                .addGap(30, 30, 30)
                                .addComponent(buttom_clearNV)
                                .addGap(43, 43, 43)
                                .addComponent(buttom_saveNV))
                            .addGroup(m4Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(m4Layout.createSequentialGroup()
                                        .addComponent(btnSetup)
                                        .addGap(33, 33, 33)
                                        .addComponent(button_importNV))
                                    .addGroup(m4Layout.createSequentialGroup()
                                        .addComponent(btnSearch)
                                        .addGap(69, 69, 69)
                                        .addComponent(fieldSearchNV, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(26, 26, 26)
                        .addComponent(button_exportNV)
                        .addGap(24, 24, 24))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_refresh1)
                .addGap(219, 219, 219))
        );
        m4Layout.setVerticalGroup(
            m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m4Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(m4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(91, 91, 91)
                        .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(fieldTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addGroup(m4Layout.createSequentialGroup()
                        .addComponent(button_refresh1)
                        .addGap(41, 41, 41)
                        .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(fieldSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addComponent(fieldNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldSearchNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addGroup(m4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttom_themNV)
                    .addComponent(buttom_clearNV)
                    .addComponent(buttom_saveNV)
                    .addComponent(button_exportNV)
                    .addComponent(button_importNV)
                    .addComponent(btnSetup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab3.addTab("tab4", jPanel1);

        jLabel51.setText("Giới tính");

        jLabel52.setText("Tổng chi tiêu");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-icon.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Pencil-icon.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete-icon.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel53.setText("Từ Khóa tìm");

        jLabel54.setText("Chi tiêu từ");

        jLabel55.setText("Đến :");

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Search-icon.png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        txtTuKhoaTim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTuKhoaTimCaretUpdate(evt);
            }
        });
        txtTuKhoaTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuKhoaTimActionPerformed(evt);
            }
        });
        txtTuKhoaTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuKhoaTimKeyReleased(evt);
            }
        });

        txtMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinActionPerformed(evt);
            }
        });

        txtMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaxActionPerformed(evt);
            }
        });

        cbxGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nu" }));

        btnFind1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Search-icon.png"))); // NOI18N
        btnFind1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFind1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxGioiTinh, 0, 221, Short.MAX_VALUE)
                            .addComponent(txtTongChiTieu)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnAdd)
                                .addGap(29, 29, 29)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel55)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTuKhoaTim))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnFind)
                                    .addComponent(btnFind1))))
                        .addGap(0, 149, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongChiTieu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTuKhoaTim, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFind1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55)
                        .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnLoadingKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Refresh-icon.png"))); // NOI18N
        btnLoadingKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadingKHActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel56.setText("QUẢN LÝ KHÁCH HÀNG");

        tableKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã khách Hàng", "Họ Tên", "Giới Tính", "Tổng chi tiêu"
            }
        ));
        tableKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKHMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tableKH);

        jLabel49.setText("Mã khách hàng");

        jLabel50.setText("Họ tên");

        javax.swing.GroupLayout m7Layout = new javax.swing.GroupLayout(m7);
        m7.setLayout(m7Layout);
        m7Layout.setHorizontalGroup(
            m7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoadingKH)
                .addGap(245, 245, 245))
            .addGroup(m7Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(m7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(m7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        m7Layout.setVerticalGroup(
            m7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(m7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLoadingKH)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(m7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(m7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(m7Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab3.addTab("tab5", jPanel7);

        tab_MENU2.setBackground(new java.awt.Color(255, 255, 255));
        tab_MENU2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_MENU2MouseClicked(evt);
            }
        });

        paneNhapHang.setPreferredSize(new java.awt.Dimension(1200, 557));
        paneNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneNhapHangMouseClicked(evt);
            }
        });

        tblKhoHang.setBackground(new java.awt.Color(204, 204, 204));
        tblKhoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "MÃ SP", "TÊN SP", "TỒN KHO"
            }
        ));
        tblKhoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoHangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblKhoHang);

        tblNhapHang.setBackground(new java.awt.Color(204, 204, 204));
        tblNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ SP", "TÊN SP ", "SỐ LƯỢNG", "ĐƠN GIÁ", "MÃ NCC", "THÀNH TIỀN"
            }
        ));
        tblNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhapHangMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblNhapHang);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel27.setText("CHI TIẾT SẢN PHẨM");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("MÃ SẢN PHẨM");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("TÊN SẢN PHẨM");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("ĐƠN GIÁ NHẬP");

        fieldGiaForNhapHang.setText(" ");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("SỐ LƯỢNG NHẬP");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setText("MÃ NCC");

        btnNhapHang.setBackground(new java.awt.Color(255, 102, 0));
        btnNhapHang.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapHang.setText("NHẬP");
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel35.setText("KHO HÀNG");

        btnXoaNhap.setBackground(new java.awt.Color(255, 102, 0));
        btnXoaNhap.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXoaNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNhap.setText("XÓA");
        btnXoaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhapActionPerformed(evt);
            }
        });

        btnXuat.setBackground(new java.awt.Color(255, 102, 0));
        btnXuat.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnXuat.setText("XUẤT PHIẾU NHẬP");
        btnXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXuatMouseClicked(evt);
            }
        });
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        btnSearchKhoHang.setText("Tìm kiếm");
        btnSearchKhoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKhoHangActionPerformed(evt);
            }
        });

        fieldMaSPForNhapHang.setText(" ");

        fieldTenSPForNhapHang.setText(" ");

        button_refresh3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Refresh-icon.png"))); // NOI18N
        button_refresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_refresh3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneNhapHangLayout = new javax.swing.GroupLayout(paneNhapHang);
        paneNhapHang.setLayout(paneNhapHangLayout);
        paneNhapHangLayout.setHorizontalGroup(
            paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneNhapHangLayout.createSequentialGroup()
                .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(paneNhapHangLayout.createSequentialGroup()
                                    .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                                            .addGap(135, 135, 135)
                                            .addComponent(jLabel35)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(button_refresh3))
                                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                                            .addGap(19, 19, 19)
                                            .addComponent(jLabel24)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnSearchKhoHang)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(fieldSearchKhoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                                            .addGap(94, 94, 94)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                                            .addGap(9, 9, 9)
                                            .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(paneNhapHangLayout.createSequentialGroup()
                                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(fieldTenSPForNhapHang))
                                                .addGroup(paneNhapHangLayout.createSequentialGroup()
                                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(fieldMaSPForNhapHang))
                                                .addGroup(paneNhapHangLayout.createSequentialGroup()
                                                    .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                                                            .addComponent(jLabel31)
                                                            .addGap(37, 37, 37))
                                                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                                                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGap(18, 18, 18)))
                                                    .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(fieldNCCForNhapHang, 0, 156, Short.MAX_VALUE)
                                                        .addComponent(fieldGiaForNhapHang)
                                                        .addComponent(fieldSoLuongForNhapHang, javax.swing.GroupLayout.Alignment.TRAILING)))))
                                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(paneNhapHangLayout.createSequentialGroup()
                                    .addGap(407, 407, 407)
                                    .addComponent(btnNhapHang)
                                    .addGap(33, 33, 33)
                                    .addComponent(btnXoaNhap)
                                    .addGap(29, 29, 29)
                                    .addComponent(btnXuat))))
                        .addGroup(paneNhapHangLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(239, Short.MAX_VALUE))
        );
        paneNhapHangLayout.setVerticalGroup(
            paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneNhapHangLayout.createSequentialGroup()
                .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneNhapHangLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel27))
                    .addGroup(paneNhapHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button_refresh3)
                            .addComponent(jLabel35))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldSearchKhoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(btnSearchKhoHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(paneNhapHangLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldMaSPForNhapHang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldTenSPForNhapHang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldGiaForNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldSoLuongForNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldNCCForNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNhapHang)
                        .addComponent(btnXoaNhap)
                        .addComponent(btnXuat)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab_MENU2.addTab("NHẬP HÀNG", paneNhapHang);

        tblCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NCC", "Mã SP", "Giá Cung Cấp", "Số Lượng", "Thành tiền"
            }
        ));
        tblCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPNMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblCTPN);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel26.setText("CHI TIẾT PHIẾU NHẬP");

        jLabel28.setText("Mã PN");

        fieldSearchCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldSearchCTPNActionPerformed(evt);
            }
        });

        btnSearchCTPH.setText("Tìm kiếm");
        btnSearchCTPH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCTPHActionPerformed(evt);
            }
        });

        jLabel34.setText("Ngày Nhập");

        jLabel36.setText("Tổng tiền");

        jLabel37.setText("Mã NCC");

        jLabel38.setText("Giá cung cấp");

        fieldGiaForPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldGiaForPNActionPerformed(evt);
            }
        });

        jLabel39.setText("Mã SP");

        jLabel40.setText("Số lượng");

        btnXoaPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete-icon.png"))); // NOI18N
        btnXoaPN.setText("Xóa");
        btnXoaPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPNActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-icon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSuaPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Pencil-icon.png"))); // NOI18N
        btnSuaPN.setText("Sửa");
        btnSuaPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaPNActionPerformed(evt);
            }
        });

        button_refresh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Refresh-icon.png"))); // NOI18N
        button_refresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_refresh2ActionPerformed(evt);
            }
        });

        tblMaPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "MaPN", "Ngày nhập"
            }
        ));
        tblMaPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMaPNMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblMaPN);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnSearchCTPH)
                                .addGap(18, 18, 18)
                                .addComponent(fieldSearchCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(maPN_CTPN)
                                .addGap(173, 173, 173)
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(ngaynhap_CTPN)
                                .addGap(182, 182, 182)
                                .addComponent(jLabel36)
                                .addGap(18, 18, 18)
                                .addComponent(tongTien_CTPN))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel37)
                                            .addComponent(jLabel39))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fieldMaSPForPN, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                            .addComponent(fieldMaNCCForPN))
                                        .addGap(155, 155, 155)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel38)
                                            .addComponent(jLabel40)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addComponent(btnXoaPN)
                                        .addGap(98, 98, 98)
                                        .addComponent(btnThem)
                                        .addGap(41, 41, 41)))
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fieldSoLuongForPN, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(fieldGiaForPN)))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(btnSuaPN))))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_refresh2)))))
                .addContainerGap(422, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(button_refresh2))
                .addGap(25, 25, 25)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(maPN_CTPN)
                    .addComponent(jLabel34)
                    .addComponent(ngaynhap_CTPN)
                    .addComponent(jLabel36)
                    .addComponent(tongTien_CTPN))
                .addGap(33, 33, 33)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(fieldMaNCCForPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(fieldGiaForPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(fieldMaSPForPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(fieldSoLuongForPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaPN)
                    .addComponent(btnThem)
                    .addComponent(btnSuaPN))
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchCTPH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldSearchCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                .addGap(181, 181, 181))
        );

        tab_MENU2.addTab("PHIẾU NHẬP", jPanel12);

        javax.swing.GroupLayout m6Layout = new javax.swing.GroupLayout(m6);
        m6.setLayout(m6Layout);
        m6Layout.setHorizontalGroup(
            m6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tab_MENU2, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        m6Layout.setVerticalGroup(
            m6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tab_MENU2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab3.addTab("tab6", jPanel8);

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));

        jLabel66.setForeground(new java.awt.Color(255, 0, 0));
        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/icons8_view_40px.png"))); // NOI18N
        jLabel66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel66MouseClicked(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("THỐNG KÊ TỔNG QUÁT");

        jPanel17.setBackground(new java.awt.Color(255, 102, 0));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("MÓN ĂN");
        jPanel17.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, -1));

        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/pizza.png"))); // NOI18N
        jPanel17.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 110, 70));

        txtCoutMONAN.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtCoutMONAN.setForeground(new java.awt.Color(255, 255, 255));
        txtCoutMONAN.setText("25");
        jPanel17.add(txtCoutMONAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 50, 40));

        jPanel18.setBackground(new java.awt.Color(255, 0, 51));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("KHÁCH HÀNG");
        jPanel18.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 170, 50));

        txtCoutKH.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtCoutKH.setForeground(new java.awt.Color(255, 255, 255));
        txtCoutKH.setText("20");
        jPanel18.add(txtCoutKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 20, 60, -1));

        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/bank-card-front-side.png"))); // NOI18N
        jPanel18.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 110, 70));

        jPanel19.setBackground(new java.awt.Color(153, 0, 153));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("NHÂN VIÊN");
        jPanel19.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 50));

        txtCoutNV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtCoutNV.setForeground(new java.awt.Color(255, 255, 255));
        txtCoutNV.setText("5");
        jPanel19.add(txtCoutNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/pizza.png"))); // NOI18N
        jPanel19.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 110, 70));

        jPanel20.setBackground(new java.awt.Color(0, 204, 0));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("TỔNG DOANH THU");
        jPanel20.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, 50));

        txtCoutDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtCoutDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        txtCoutDoanhThu.setText("5");
        jPanel20.add(txtCoutDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, -1));

        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/people-carry.png"))); // NOI18N
        jPanel20.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 110, 50));

        tbTOPBAnCHAY.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane12.setViewportView(tbTOPBAnCHAY);

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("TOP BÁN CHẠY");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(448, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel76)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab3.addTab("tab7", jPanel9);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("DOANH THU NĂM");

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/icons8_undo_40px.png"))); // NOI18N
        jLabel78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel78MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(jLabel78))
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab3.addTab("tab8", jPanel21);

        jPanel3.add(tab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 86, 790, 630));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
        tab3.setSelectedIndex(0);
    }//GEN-LAST:event_p1MouseClicked

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        tab3.setSelectedIndex(1);
    }//GEN-LAST:event_p2MouseClicked

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        tab3.setSelectedIndex(2);
    }//GEN-LAST:event_p3MouseClicked

    private void P7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P7MouseClicked
        JFrame jframe = new JFrame("EXIT");
        if (JOptionPane.showConfirmDialog(jframe, "BẠN CÓ MUỐN ĐĂNG XUẤT KHÔNG?", "ĐĂNG XUẤT ", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
            DangNhap dn = new DangNhap();
            dn.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_P7MouseClicked

    private void P4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P4MouseClicked
        tab3.setSelectedIndex(3);
    }//GEN-LAST:event_P4MouseClicked

    private void P5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P5MouseClicked
        tab3.setSelectedIndex(4);
    }//GEN-LAST:event_P5MouseClicked

    private void P6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P6MouseClicked
        tab3.setSelectedIndex(6);
    }//GEN-LAST:event_P6MouseClicked

    private void p1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseReleased
        p1.setForeground(yellow);
        p2.setForeground(white);
        p3.setForeground(white);
        P4.setForeground(white);
        P5.setForeground(white);
        P6.setForeground(white);
        P7.setForeground(white);
        P8.setForeground(white);

        p1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        p2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P7.setFont(new Font("Segoe UI", Font.BOLD, 18));
    }//GEN-LAST:event_p1MouseReleased

    private void p2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseReleased
        p1.setForeground(white);
        p2.setForeground(yellow);
        p3.setForeground(white);
        P4.setForeground(white);
        P5.setForeground(white);
        P6.setForeground(white);
        P7.setForeground(white);
        P8.setForeground(white);

        p1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        p3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P7.setFont(new Font("Segoe UI", Font.BOLD, 18));
    }//GEN-LAST:event_p2MouseReleased

    private void p3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseReleased
        p1.setForeground(white);
        p2.setForeground(white);
        p3.setForeground(yellow);
        P4.setForeground(white);
        P5.setForeground(white);
        P6.setForeground(white);
        P7.setForeground(white);
        P8.setForeground(white);
        p1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p3.setFont(new Font("Segoe UI", Font.BOLD, 20));
        P4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P7.setFont(new Font("Segoe UI", Font.BOLD, 18));
    }//GEN-LAST:event_p3MouseReleased

    private void P4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P4MouseReleased
        p1.setForeground(white);
        p2.setForeground(white);
        p3.setForeground(white);
        P4.setForeground(yellow);
        P5.setForeground(white);
        P6.setForeground(white);
        P7.setForeground(white);
        P8.setForeground(white);

        p1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P4.setFont(new Font("Segoe UI", Font.BOLD, 20));
        P5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P7.setFont(new Font("Segoe UI", Font.BOLD, 18));
    }//GEN-LAST:event_P4MouseReleased

    private void P5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P5MouseReleased
        p1.setForeground(white);
        p2.setForeground(white);
        p3.setForeground(white);
        P4.setForeground(white);
        P5.setForeground(yellow);
        P6.setForeground(white);
        P7.setForeground(white);
        P8.setForeground(white);
        p1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P5.setFont(new Font("Segoe UI", Font.BOLD, 20));
        P6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P7.setFont(new Font("Segoe UI", Font.BOLD, 18));
    }//GEN-LAST:event_P5MouseReleased

    private void P6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P6MouseReleased
        p1.setForeground(white);
        p2.setForeground(white);
        p3.setForeground(white);
        P4.setForeground(white);
        P5.setForeground(white);
        P6.setForeground(yellow);
        P7.setForeground(white);
        P8.setForeground(white);
        p1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P6.setFont(new Font("Segoe UI", Font.BOLD, 20));
        P7.setFont(new Font("Segoe UI", Font.BOLD, 18));
    }//GEN-LAST:event_P6MouseReleased

    private void P7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P7MouseReleased
        p1.setForeground(white);
        p2.setForeground(white);
        p3.setForeground(white);
        P4.setForeground(white);
        P6.setForeground(white);
        P7.setForeground(yellow);
        P8.setForeground(white);
        p1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P7.setFont(new Font("Segoe UI", Font.BOLD, 20));
    }//GEN-LAST:event_P7MouseReleased

    private void tab_MENU1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_MENU1MouseClicked

    }//GEN-LAST:event_tab_MENU1MouseClicked

    private void tab_banHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_banHangMouseClicked

    }//GEN-LAST:event_tab_banHangMouseClicked

    private void buttonDelSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDelSaleActionPerformed
        // TODO add your handling code here:
        int vitri = tbGH.getSelectedRow();
        if (vitri < 0) {
            JOptionPane.showMessageDialog(this, "bạn chưa chọn sản phầm nào! ");
        } else {
            for (int i = 0; i < l.size(); i++) {
                if (i == vitri) {
                    listCOPPY.remove(vitri);
                    l.remove(i);
                    soluong.remove(i);
                }
            }
            fillTableGH();
        }


    }//GEN-LAST:event_buttonDelSaleActionPerformed

    private void P8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P8MouseClicked
        tab3.setSelectedIndex(5);
    }//GEN-LAST:event_P8MouseClicked

    private void P8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P8MouseReleased
        p1.setForeground(white);
        p2.setForeground(white);
        p3.setForeground(white);
        P4.setForeground(white);
        P5.setForeground(white);
        P6.setForeground(white);
        P7.setForeground(white);
        P8.setForeground(yellow);

        P8.setFont(new Font("Segoe UI", Font.BOLD, 20));
        p1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        P7.setFont(new Font("Segoe UI", Font.BOLD, 18));
    }//GEN-LAST:event_P8MouseReleased

    private void exportBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportBillMouseClicked
        InHoaDon ihd = null;
        
        if (listCOPPY.isEmpty()) {
            JOptionPane.showMessageDialog(this, " BẠN CHƯA CHỌN SẢN PHẨM NÀO HẾT ! ");
        } else {
            try {
                
                ihd = new InHoaDon(listCOPPY);
                
            } catch (SQLException ex) {
                Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            ihd.setVisible(true);

            
            ihd.setMaNV((String) ComboxNhanVien.getSelectedItem());
            this.showDATA();
        }
        
        ihd.setNewLIST(listCOPPY);


    }//GEN-LAST:event_exportBillMouseClicked

    private void fieldTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTenSPActionPerformed

    private void fieldSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSoLuongActionPerformed

    private void fieldDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDonGiaActionPerformed

    private void buttom_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttom_clearActionPerformed
        // TODO add your handling code here:
        buttom_clear.setEnabled(false);
        buttom_save.setEnabled(false);
        this.deleteSPForProduct();
        //this.fillTableForFormProduct();
        //listSearch.clear();
        sreachSPForForm(keySearchSP);
        fillTableForFormProductSearch();

    }//GEN-LAST:event_buttom_clearActionPerformed

    private void button_importActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_importActionPerformed
        JFileChooser c = new JFileChooser();
        int rVal = c.showOpenDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String filename = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();
            String filePath = dir + "\\" + filename;
            try {
                // TODO add your handling code here:
                this.autoUpdate(filePath);
            } catch (IOException ex) {
                Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.fillTableForFormProduct();
    }//GEN-LAST:event_button_importActionPerformed

    private void button_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_exportActionPerformed
        JFileChooser c = new JFileChooser();
        int rVal = c.showSaveDialog(null);
        String filePath;
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String filename = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();
            filePath = dir + "\\" + filename;
            //System.out.println(filePath);
            try {
                // TODO add your handling code here:
                exportExel(filePath);
            } catch (IOException ex) {
                Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_button_exportActionPerformed

    private void fieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSearchActionPerformed

    private void buttom_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttom_themActionPerformed
        // TODO add your handling code here:
        buttom_save.setEnabled(false);
        buttom_clear.setEnabled(false);
        this.addSPForForm();
        this.fillTableForFormProduct();

    }//GEN-LAST:event_buttom_themActionPerformed

    private void tableSP_formSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSP_formSPMouseClicked
        // TODO add your handling code here:
        this.showDetail();
        buttom_save.setEnabled(true);
        buttom_clear.setEnabled(true);
    }//GEN-LAST:event_tableSP_formSPMouseClicked

    String keySearchSP;
    private void button_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_searchActionPerformed
        // TODO add your handling code here:
        buttom_save.setEnabled(false);
        buttom_clear.setEnabled(false);
        keySearchSP = fieldSearch.getText().trim();
        sreachSPForForm(keySearchSP);
        fillTableForFormProductSearch();
    }//GEN-LAST:event_button_searchActionPerformed

    private void button_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_refreshActionPerformed
        // TODO add your handling code here:
        buttom_save.setEnabled(false);
        buttom_clear.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        buttom_saveNV.setEnabled(false);
        btnSetup.setEnabled(false);
        btnXoaNhap.setEnabled(false);
        btnXoaPN.setEnabled(false);
        btnSuaPN.setEnabled(false);
        fieldTenSP.setText("");
        fieldMaSP.setText("");
        fieldDonViTinh.setText("");
        fieldDonGia.setText("");
        fieldSoLuong.setText("");
        fieldSearch.setText("");
        listSearch.clear();
        fillTableForFormProduct();

    }//GEN-LAST:event_button_refreshActionPerformed

    private void buttom_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttom_saveActionPerformed
        // TODO add your handling code here:
        buttom_save.setEnabled(false);
        buttom_clear.setEnabled(false);
        this.updateSPForProduct();
        //this.fillTableForFormProduct();
        //listSearch.clear();
        sreachSPForForm(keySearchSP);
        fillTableForFormProductSearch();


    }//GEN-LAST:event_buttom_saveActionPerformed

    private void fieldDonViTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDonViTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDonViTinhActionPerformed

    private void tbGHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGHMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbGHMouseClicked

    private void tbSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSPMouseClicked
        // TODO add your handling code here:
        int vitri = tbSP.getSelectedRow();

        hideRow(vitri);
    }//GEN-LAST:event_tbSPMouseClicked

    List<Integer> soluong = new ArrayList<>();
    private void btnAddBagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBagActionPerformed
        // TODO add your handling code here:
        listCOPPY.clear();
        int vitri = tbSP.getSelectedRow();
        if (vitri < 0) {
            JOptionPane.showMessageDialog(this, "bạn chưa chọn sản phầm nào! ");
        } else {
            if (tonTai(vitri)) {
                JOptionPane.showMessageDialog(this, "sản phẩm này đã có trong giỏ hàng ");
            } else {
                List<SANPHAM> listSP = getAllSP();
                SANPHAM sp = new SANPHAM();
                //List<SANPHAM> listGH = new ArrayList<>();
                l.add(String.valueOf(vitri));
                for (int i = 0; i < l.size(); i++) {
                    sp = listSP.get(Integer.parseInt(l.get(i)));
                    if (i == l.size() - 1 && (int) spinerSP.getValue() <= listSP.get(vitri).getSoLuongConLai() && (int) spinerSP.getValue() > 0 ) {
                        sp.setSoLuongConLai((int) spinerSP.getValue());
                        soluong.add((int) spinerSP.getValue());
                        listCOPPY.add(sp);
                    } else if (i == l.size() - 1 && (int) spinerSP.getValue() > listSP.get(vitri).getSoLuongConLai()) {
                        JOptionPane.showMessageDialog(this, "đã vượt quá số lượng hàng còn tồn kho");
                        l.remove(i);
                    }else if (i == l.size() - 1 && (int) spinerSP.getValue() <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn không");
                        l.remove(i);
                    }else {
                        sp.setSoLuongConLai(soluong.get(i));
                        listCOPPY.add(sp);
                    }

                }
                fillTableGH();
            }

        }


    }//GEN-LAST:event_btnAddBagActionPerformed

    private void fieldNamSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNamSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNamSinhActionPerformed

    private void fieldTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTenNVActionPerformed

    String keySearch;
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        buttom_saveNV.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        btnSetup.setEnabled(false);
        keySearch = fieldSearchNV.getText().trim();
        sreachNVForForm();
        fillTableForFormNVSearch();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void buttom_themNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttom_themNVActionPerformed
        buttom_saveNV.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        btnSetup.setEnabled(false);
        try {
            // TODO add your handling code here:
            this.addNVForForm();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fillTableForFormNV();
    }//GEN-LAST:event_buttom_themNVActionPerformed

    private void buttom_saveNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttom_saveNVActionPerformed
        buttom_saveNV.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        btnSetup.setEnabled(false);

        try {
            // TODO add your handling code here:
            this.updateNVForForm();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.fillTableForFormNV();
        //listSearchNV.clear();
        sreachNVForForm();
        fillTableForFormNVSearch();

    }//GEN-LAST:event_buttom_saveNVActionPerformed

    private void buttom_clearNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttom_clearNVActionPerformed
        // TODO add your handling code here:
        buttom_saveNV.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        btnSetup.setEnabled(false);
        this.deleteNVForProduct();
//        this.fillTableForFormNV();
//        listSearchNV.clear();
        sreachNVForForm();
        fillTableForFormNVSearch();
    }//GEN-LAST:event_buttom_clearNVActionPerformed

    private void button_exportNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_exportNVActionPerformed
        // TODO add your handling code here:
        JFileChooser c = new JFileChooser();
        int rVal = c.showSaveDialog(null);
        String filePath;
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String filename = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();
            filePath = dir + "\\" + filename;
            // System.out.println(filePath);
            try {
                // TODO add your handling code here:
                exportExelNV(filePath);
            } catch (IOException ex) {
                Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_button_exportNVActionPerformed

    private void button_importNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_importNVActionPerformed
        // TODO add your handling code here:
        JFileChooser c = new JFileChooser();
        int rVal = c.showOpenDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String filename = c.getSelectedFile().getName();
            String dir = c.getCurrentDirectory().toString();
            String filePath = dir + "\\" + filename;
            try {
                // TODO add your handling code here:
                this.autoUpdateNV(filePath);
            } catch (IOException ex) {
                Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "lỗi không nhập được ", "  ", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParseException ex) {
                Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "lỗi không nhập được ", "  ", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("lỗi osaodnfosndfknsdnflknskdf             ádfasdf");
            }
        }
        this.fillTableForFormNV();
    }//GEN-LAST:event_button_importNVActionPerformed


    private void tableNV_formNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNV_formNVMouseClicked
        // TODO add your handling code here:
        this.showDetailNV();
        buttom_saveNV.setEnabled(true);
        buttom_clearNV.setEnabled(true);
        btnSetup.setEnabled(true);
    }//GEN-LAST:event_tableNV_formNVMouseClicked

    private void button_refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_refresh1ActionPerformed
        // TODO add your handling code here:
        fieldTenNV.setText("");
        fieldMaNV.setText("");
        fieldSex.setText("");
        fieldNamSinh.setText("");
        fieldSearchNV.setText("");
        buttom_save.setEnabled(false);
        buttom_clear.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        buttom_saveNV.setEnabled(false);
        btnSetup.setEnabled(false);
        btnXoaNhap.setEnabled(false);
        btnXoaPN.setEnabled(false);
        btnSuaPN.setEnabled(false);
        fillTableForFormNV();
        listSearchNV.clear();

    }//GEN-LAST:event_button_refresh1ActionPerformed

    private void btnSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupActionPerformed
        // TODO add your handling code here:
        //btnSetup.setEnabled(false);
        String s = fieldMaNV.getText();
        FormTaiKhoan dialog = new FormTaiKhoan(new javax.swing.JFrame(), true, s);
        dialog.setVisible(true);

    }//GEN-LAST:event_btnSetupActionPerformed

    private void tab_MENU2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_MENU2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tab_MENU2MouseClicked

    private void paneNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneNhapHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_paneNhapHangMouseClicked

    private void btnXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatMouseClicked

    private void btnXoaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhapActionPerformed
        // TODO add your handling code here:
        btnXoaNhap.setEnabled(false);
        deleteNHForForm();
        fillTableNhapHang();
    }//GEN-LAST:event_btnXoaNhapActionPerformed


    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        // TODO add your handling code here:

        if (fieldSoLuongForNhapHang.getText().isEmpty() || fieldGiaForNhapHang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống số lượng , đơn giá ", "  ", JOptionPane.INFORMATION_MESSAGE);
        } else {
            PHIEUNHAP pn = new PHIEUNHAP();
            pn.setMaPN(MaPN);
            pn.setMaSP(fieldMaSPForNhapHang.getText());
            //System.out.println(pn.getMaSP() + "trong btn");
            pn.setSoLuongNhap(Integer.parseInt(fieldSoLuongForNhapHang.getText()));
            pn.setGiaCungCap(Double.parseDouble(fieldGiaForNhapHang.getText()));
            pn.setMaNCC((String) fieldNCCForNhapHang.getSelectedItem());
            pn.setThanhTien(Integer.parseInt(fieldSoLuongForNhapHang.getText()) * Double.parseDouble(fieldGiaForNhapHang.getText()));
            Date date = new Date();
            String s5 = new SimpleDateFormat("dd/MM/yyyy").format(date);
            try {
                pn.setNgayNhap(s5);
            } catch (ParseException ex) {
                Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (existNH(pn.getMaNCC(), pn.getMaSP()) == false) {
                listNH.add(pn);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Nhà cung cấp đã cung cấp sản phầm này rồi ", "  ", JOptionPane.INFORMATION_MESSAGE);
            }

            fillTableNhapHang();

        }


    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void tblNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhapHangMouseClicked
        // TODO add your handling code here:
        btnXoaNhap.setEnabled(true);
    }//GEN-LAST:event_tblNhapHangMouseClicked

    private void tblKhoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoHangMouseClicked
        // TODO add your handling code here:
        showDetailKhoHang();
    }//GEN-LAST:event_tblKhoHangMouseClicked

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        if (listNH.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn hàng để nhập", "  ", JOptionPane.INFORMATION_MESSAGE);
        } else {
            addAllNH();
            ArrayList<PHIEUNHAP> listPN = (ArrayList<PHIEUNHAP>) getAllPNKey(MaPN.trim());
            PHIEUNHAP pn = listPN.get(0);
            // fieldSearchCTPN.setText(pn.getMaPN());
            maPN_CTPN.setText(pn.getMaPN());
            String s5 = new SimpleDateFormat("dd/MM/yyyy").format(pn.getNgayNhap());
            ngaynhap_CTPN.setText(s5);
            double tongtien = 0;
            for (PHIEUNHAP pn1 : listPN) {
                tongtien = tongtien + pn1.getThanhTien();
            }
            tongTien_CTPN.setText(String.valueOf(tongtien));
            fillTableCTPN(MaPN);
            MaPN = "pn" + String.valueOf(count());
            autoUpdateKhoHang();
            listNH.clear();
            fillTableKhoHang();
            fillTableNhapHang();
            JOptionPane.showMessageDialog(rootPane, "Bạn đã nhập thành công ,qua phiếu nhập để kiểm tra lại ", "  ", JOptionPane.INFORMATION_MESSAGE);
            fillTableCMaPN();

        }


    }//GEN-LAST:event_btnXuatActionPerformed

    private void btnSearchCTPHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCTPHActionPerformed
        // TODO add your handling code here:
        fillTableCMaPNSearch();
    }//GEN-LAST:event_btnSearchCTPHActionPerformed

    private void fieldGiaForPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldGiaForPNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldGiaForPNActionPerformed

    private void btnSuaPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaPNActionPerformed
        btnSuaPN.setEnabled(false);
        btnXoaPN.setEnabled(false);
        try {
            // TODO add your handling code here:
            updatePNForForm();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fillTableCTPN(maPN_CTPN.getText());
        fillTableKhoHang();
    }//GEN-LAST:event_btnSuaPNActionPerformed

    private void tblCTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPNMouseClicked
        // TODO add your handling code here:
        showDetailPN();
        btnXoaPN.setEnabled(true);
        btnSuaPN.setEnabled(true);
    }//GEN-LAST:event_tblCTPNMouseClicked

    private void button_refresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_refresh2ActionPerformed
        // TODO add your handling code here:
        fieldMaNCCForPN.setText("");
        fieldMaSPForPN.setText("");
        fieldGiaForPN.setText("");
        fieldSoLuongForPN.setText("");
        buttom_save.setEnabled(false);
        buttom_clear.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        buttom_saveNV.setEnabled(false);
        btnSetup.setEnabled(false);
        btnXoaNhap.setEnabled(false);
        btnXoaPN.setEnabled(false);
        btnSuaPN.setEnabled(false);
        fillTableCMaPN();
        listPNSearch.clear();
    }//GEN-LAST:event_button_refresh2ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnXoaPN.setEnabled(false);
        btnSuaPN.setEnabled(false);
        try {
            // TODO add your handling code here:
            addPNForForm();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        fillTableCTPN(maPN_CTPN.getText());
        fillTableKhoHang();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPNActionPerformed
        // TODO add your handling code here:
        btnXoaPN.setEnabled(false);
        btnSuaPN.setEnabled(false);
        deletePNForForm();
        this.fillTableCTPN(maPN_CTPN.getText());
        fillTableCMaPN();
        fillTableKhoHang();

    }//GEN-LAST:event_btnXoaPNActionPerformed

    private void fieldSearchCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldSearchCTPNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSearchCTPNActionPerformed

    private void tblMaPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMaPNMouseClicked
        // TODO add your handling code here:
        showDetailPNTbl();
        //fillTableCMaPN();


    }//GEN-LAST:event_tblMaPNMouseClicked

    private void btnSearchKhoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKhoHangActionPerformed
        // TODO add your handling code here:
        sreachSPForFormNhapHang(fieldSearchKhoHang.getText());
        fillTableKhoHangSearch();
    }//GEN-LAST:event_btnSearchKhoHangActionPerformed

    private void button_refresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_refresh3ActionPerformed
        // TODO add your handling code here:
        fillTableKhoHang();
        buttom_save.setEnabled(false);
        buttom_clear.setEnabled(false);
        buttom_clearNV.setEnabled(false);
        buttom_saveNV.setEnabled(false);
        btnSetup.setEnabled(false);
        btnXoaNhap.setEnabled(false);
        btnXoaPN.setEnabled(false);
        btnSuaPN.setEnabled(false);
        listSearch1.clear();
    }//GEN-LAST:event_button_refresh3ActionPerformed

    private void txtMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKMActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);

        try {
            this.addKMForForm();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.fillTableForFormKM();

    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        try {
            this.updateKMForForm();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fillTableForFormKM();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

        this.deleteKMForForm();
        this.fillTableForFormKM();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tableMaKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMaKMMouseClicked
        try {
            this.showDetailKM();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnThem.setEnabled(true);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tableMaKMMouseClicked

    private void btnLoadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadingActionPerformed
        resetFormKM();
    }//GEN-LAST:event_btnLoadingActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
        try {
            this.addKHForForm();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listSeachKH.isEmpty() && listSeachKH1.isEmpty()) {
            fillTableForFormKH();
        } else if (listSeachKH.isEmpty() == false && listSeachKH1.isEmpty() == true) {
            sreachKHCTForForm();
            fillTableForFormKHSearch();
        } else {
            sreachKHForFormName();
            fillTableForFormKHSearchName();
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);

        try {
            this.updateKHForForm();
        } catch (ParseException ex) {
            Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listSeachKH.isEmpty() && listSeachKH1.isEmpty()) {
            fillTableForFormKH();
        } else if (listSeachKH.isEmpty() == false && listSeachKH1.isEmpty() == true) {
            sreachKHCTForForm();
            fillTableForFormKHSearch();
        } else {
            sreachKHForFormName();
            fillTableForFormKHSearchName();
        }


    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        btnDelete.setEnabled(false);
        btnSave.setEnabled(false);
        this.deleteKHForForm();
        if (listSeachKH.isEmpty() && listSeachKH1.isEmpty()) {
            fillTableForFormKH();
        } else if (listSeachKH.isEmpty() == false && listSeachKH1.isEmpty() == true) {
            sreachKHCTForForm();
            fillTableForFormKHSearch();
        } else {
            sreachKHForFormName();
            fillTableForFormKHSearchName();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
        min = Double.parseDouble(txtMin.getText());
        max = Double.parseDouble(txtMax.getText());
        sreachKHCTForForm();
        fillTableForFormKHSearch();
        listSeachKH1.clear();
    }//GEN-LAST:event_btnFindActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtTuKhoaTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTuKhoaTimCaretUpdate

    }//GEN-LAST:event_txtTuKhoaTimCaretUpdate

    private void txtTuKhoaTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuKhoaTimActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTuKhoaTimActionPerformed

    private void txtTuKhoaTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuKhoaTimKeyReleased

    }//GEN-LAST:event_txtTuKhoaTimKeyReleased

    private void btnLoadingKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadingKHActionPerformed
        txtMaKH.setText("");
        txtTenKH.setText("");
        cbxGioiTinh.setSelectedItem("");
        txtTongChiTieu.setText("");
        txtTuKhoaTim.setText("");
        txtMin.setText("");
        txtMax.setText("");
        btnSave.setEnabled(false);
        btnDelete.setEnabled(false);
        fillTableForFormKH();
        listSeachKH.clear();
        listSeachKH1.clear();
    }//GEN-LAST:event_btnLoadingKHActionPerformed

    private void tableKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKHMouseClicked
        this.showDetailKH();
        //this.fillTableForFormKH();
        btnSave.setEnabled(true);
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_tableKHMouseClicked

    private void Date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Date2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Date2ActionPerformed

    private void txtMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaxActionPerformed

    private void txtMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinActionPerformed

    private void btnFind1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFind1ActionPerformed
        // TODO add your handling code here:
        listSeachKH.clear();
        keySearchKH = txtTuKhoaTim.getText().trim();
        sreachKHForFormName();
        fillTableForFormKHSearchName();
    }//GEN-LAST:event_btnFind1ActionPerformed

    private void tbDSHDCHILMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDSHDCHILMouseClicked
        int vitri = tbDSHDCHIL.getSelectedRow();
        //System.out.println(vitri);

        handleFillTBChiTietHoaDon(vitri);
    }//GEN-LAST:event_tbDSHDCHILMouseClicked

    private void tbCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTHDMouseClicked
        int vitri = tbCTHD.getSelectedRow();
        handleFillCTHD(vitri);
    }//GEN-LAST:event_tbCTHDMouseClicked

    private void txtSOLUONGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSOLUONGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSOLUONGActionPerformed

    private void txtTENSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTENSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTENSPActionPerformed

    private void txtDOnGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDOnGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOnGiaActionPerformed

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        // TODO add your handling code here:
        showDataHoaDon();
        txtDOnGia.setText("");
        txtSOLUONG.setText("");
        txtTENSP.setText("");
        fieldTien.setText("");
        lableMHD.setText("");
        lableMKH.setText("");
        labletongtien.setText("");
        listHandle.clear();
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);

        Vector column = new Vector();
        column.add("MÃ HD");
        column.add("MÃ SP");
        column.add("Số Lương");
        column.add("Giá");
        column.add("Thành Tiền");
        model.setColumnIdentifiers(column);
        for (int i = 0; i < listHandle.size(); i++) {
            CHITIETHOADON ct = (CHITIETHOADON) listHandle.get(i);
            Vector row = new Vector();
            row.add(ct.getMaHD());
            row.add(ct.getMaSP());
            row.add(ct.getSoLuong());
            row.add(ct.getGia());
            row.add(ct.getSoLuong() * ct.getGia());
            model.addRow(row);

        }
        tbCTHD.setModel(model);


    }//GEN-LAST:event_jLabel64MouseClicked

    private void jLabel66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseClicked
        tab3.setSelectedIndex(7);
    }//GEN-LAST:event_jLabel66MouseClicked

    private void jLabel78MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MouseClicked
        // TODO add your handling code here:
        tab3.setSelectedIndex(6);
    }//GEN-LAST:event_jLabel78MouseClicked

    private void jLabel79MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel79MouseClicked
        // TODO add your handling code here:
        showDATA();
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtDonGia.setText("");
        spinerSP.setValue(0);
        listCOPPY.clear();
        l.clear();
        soluong.clear();
        fillTableGH();
        fillTableForFormKH();
        
    }//GEN-LAST:event_jLabel79MouseClicked

    ////////////////////////////////////code by huu 77
    //đổ dữ liệu vào giỏ hàng
    public void fillTableGH() {

        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);

        /* Tạo mới Vector chứa Column Header */
        Vector column = new Vector();
        column.add("MÃ SP");
        column.add("TEN_SP");
        column.add("GIA");
        column.add("TỒN KHO");
        column.add("LOẠI");
        /* Set Column Header lên DefaultTableModel */
        model.setColumnIdentifiers(column);
        for (int i = 0; i < listCOPPY.size(); i++) {
            SANPHAM spGH = (SANPHAM) listCOPPY.get(i);
            Vector row = new Vector();
            row.add(spGH.getMaSP());
            row.add(spGH.getName());
            row.add(spGH.getGia());
            row.add(spGH.getSoLuongConLai());
            row.add(spGH.getMaLoai());
            model.addRow(row);

        }
        tbGH.setModel(model);
    }

    //hàm này click vô thì hiện lên form bán hàng
    public void hideRow(int i) {
        List<SANPHAM> listSP = getAllSP();

        SANPHAM sp = listSP.get(i);
        comboboxSP.setSelectedItem(sp.getMaLoai());
        txtMaSP.setText(sp.getMaSP());
        txtDonGia.setText(String.valueOf(sp.getGia()));
        txtTenSP.setText(sp.getName());
        //spinerSP.setValue(sp.getSoLuongConLai());
    }

    //hàm này kiểm trả index lựa chọn có tồn tại hay chưa
    public boolean tonTai(int key) {
        for (String h : l) {
            if (key == Integer.parseInt(h)) {
                return true;
            }
        }
        return false;
    }

    //hàm này dùng đề đưa dữa liệu lên table ban hàng    
    public void showDATA() {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);

        /* Tạo mới Vector chứa Column Header */
        Vector column = new Vector();
        column.add("MÃ SP");
        column.add("TEN_SP");
        column.add("GIA");
        column.add("SOLUONGSPCON");
        column.add("LOẠI");

        /* Set Column Header lên DefaultTableModel */
        model.setColumnIdentifiers(column);

        List<SANPHAM> listSP = getAllSP();
        for (int i = 0; i < listSP.size(); i++) {
            SANPHAM sp = (SANPHAM) listSP.get(i);
            //System.out.println(sp.getMaSP());
            Vector row = new Vector();
            row.add(sp.getMaSP());
            row.add(sp.getName());
            row.add(sp.getGia());
            row.add(sp.getSoLuongConLai());
            row.add(sp.getMaLoai());
            model.addRow(row);
            //System.out.println(listSP);
        }
        tbSP.setModel(model);
    }

    public List<CHITIETHOADON> listHandle = new ArrayList<>();

    //hàm này dùng để đổ dữ liệu vào bảng hóa đơn
    public void showDataHoaDon() {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);

        /* Tạo mới Vector chứa Column Header */
        Vector column = new Vector();
        column.add("MÃ HD");
        column.add("Thanh Tien");
        column.add("TÊN NHÂN VIÊN");
        column.add("NGÀY LẬP");
        /* Set Column Header lên DefaultTableModel */
        model.setColumnIdentifiers(column);

        List<HOADON> listHD = showHoaDon();
        for (int i = 0; i < listHD.size(); i++) {
            HOADON hd = (HOADON) listHD.get(i);
            Vector row = new Vector();
            row.add(hd.getMaHD());
            row.add(hd.getThanhTien());
            row.add(hd.getTen());
            row.add(hd.getNgaylap());
            model.addRow(row);

        }
        tbDSHDCHIL.setModel(model);
    }

    //hàm này dùng để click bảng các hóa đơn thì nó hiện lên chi tiết hóa đơn
    private void handleFillTBChiTietHoaDon(int vitri) {
        listHandle.clear();
        List<HOADON> list = showHoaDon();
        HOADON hd = list.get(vitri);
        //System.out.println(hd.getThanhTien());
        List<CHITIETHOADON> listCTHD = showChiTietHoaDon();

        for (CHITIETHOADON cthd : listCTHD) {
            if (hd.getMaHD().equalsIgnoreCase(cthd.getMaHD())) {
                listHandle.add(cthd);

            }

        }
        lableMHD.setText(listHandle.get(0).getMaHD());
        labletongtien.setText(String.valueOf(listHandle.get(0).getThanhTien()));
        lableMKH.setText(listHandle.get(0).getMaKH());
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);

        Vector column = new Vector();
        column.add("MÃ HD");
        column.add("MÃ SP");
        column.add("Số Lương");
        column.add("Giá");
        column.add("Thành Tiền");
        model.setColumnIdentifiers(column);
        for (int i = 0; i < listHandle.size(); i++) {
            CHITIETHOADON ct = (CHITIETHOADON) listHandle.get(i);
            Vector row = new Vector();
            row.add(ct.getMaHD());
            row.add(ct.getMaSP());
            row.add(ct.getSoLuong());
            row.add(ct.getGia());
            row.add(ct.getSoLuong() * ct.getGia());
            model.addRow(row);

        }
        tbCTHD.setModel(model);
    }

    //hàm này click vào thì nó hiện lên
    private void handleFillCTHD(int vitri) {
        List<CHITIETHOADON> list = listHandle;
        CHITIETHOADON cthd = list.get(vitri);
        //txtMHD.setText(cthd.getMaHD());
        txtTENSP.setText(cthd.getMaSP());
        txtSOLUONG.setText(String.valueOf(cthd.getSoLuong()));
        //txtThanhTien.setText(String.valueOf(cthd.getThanhTien()));
        txtDOnGia.setText(String.valueOf(cthd.getGia()));
        fieldTien.setText(String.valueOf(cthd.getGia() * cthd.getSoLuong()));
    }

    /////////////////////////////////////////////////////////các hàm đồ họa 
    /////////////////////////////////////////// code by huu77 
    //hàm này dùng để tảo biểu đồ cho trang tiếp theo
    public void showChart() {

        getContentPane().setBackground(new Color(250, 250, 250));
        chart.addLegend("Doanh SỐ", new Color(135, 189, 245));
//        chart.addLegend("Expense", new Color(135, 189, 245));
//        chart.addLegend("Profit", new Color(189, 135, 245));
//        chart.addLegend("Cost", new Color(139, 229, 222));
        Double d1 = coutDoanhThuChart(1);
        Double d2 = coutDoanhThuChart(2);
        Double d3 = coutDoanhThuChart(3);
        Double d4 = coutDoanhThuChart(4);
        Double d5 = coutDoanhThuChart(5);
        Double d6 = coutDoanhThuChart(6);
        Double d7 = coutDoanhThuChart(7);
        Double d8 = coutDoanhThuChart(8);
        Double d9 = coutDoanhThuChart(9);
        Double d10 = coutDoanhThuChart(10);
        Double d11 = coutDoanhThuChart(11);
        Double d12 = coutDoanhThuChart(12);
        chart.addData(new ModelChart("1", new double[]{d1}));
        chart.addData(new ModelChart("2", new double[]{d2}));
        chart.addData(new ModelChart("3", new double[]{d3}));
        chart.addData(new ModelChart("4", new double[]{d4}));
        chart.addData(new ModelChart("5", new double[]{d5}));
        chart.addData(new ModelChart("6", new double[]{d6}));
        chart.addData(new ModelChart("7", new double[]{d7}));
        chart.addData(new ModelChart("8", new double[]{d8}));
        chart.addData(new ModelChart("9", new double[]{d9}));
        chart.addData(new ModelChart("10", new double[]{d10}));
        chart.addData(new ModelChart("11", new double[]{d11}));
        chart.addData(new ModelChart("12", new double[]{d12}));
    }

    public void showTOpBANCHAY() {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);

        Vector column = new Vector();
        column.add("STT");
        column.add("Tên Sản Phẩm");
        column.add("Đã Bán");
        model.setColumnIdentifiers(column);
        List<TOPTHONGKE> listTTK = limitTOP();
        for (int i = 0; i < listTTK.size(); i++) {
            TOPTHONGKE hd = (TOPTHONGKE) listTTK.get(i);
            Vector row = new Vector();
            row.add(i + 1);
            row.add(hd.getTopTenSp());
            row.add(hd.getTopSoLuong());

            model.addRow(row);

        }
        tbTOPBAnCHAY.setModel(model);
    }

    public void showCoutThongKe() {
        txtCoutMONAN.setText(coutSP());
        txtCoutKH.setText(coutKH());
        txtCoutNV.setText(coutNV());
        txtCoutDoanhThu.setText(coutDoanhThu());
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new viewMain().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(viewMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    //code for form product made by Mai Hai   
    //hàm này dùng để điền dữ liệu từ database lên table form
    public void fillTableForFormProduct() {
        ArrayList<SANPHAM> listSP1 = (ArrayList<SANPHAM>) getAllSP();
        DefaultTableModel model = (DefaultTableModel) tableSP_formSP.getModel();
        model.setRowCount(0);
        for (SANPHAM sp : listSP1) {
            // System.out.println(sp.getMaSP());
            Object[] row = new Object[]{sp.getMaSP(), sp.getTen_SP(), sp.getMaLoai(), sp.getGia(), sp.getSoLuongConLai(), sp.getDon_vi_tinh()};
            model.addRow(row);
        }
    }

    //hàm này dùng để đổ dữ liệu vào combobox trong form sản phẩm
    public void fillComboboxProduct() {
        ArrayList<LOAI> listL = (ArrayList<LOAI>) getAllLoai();
        for (LOAI l : listL) {
            //System.out.println(l.getMaLoai());
            comboBoxLoai.addItem(l.getMaLoai());
        }
    }

    //hàm này dùng để đổ dữ liệu và combox loại ở form bán hàng
    public void fillComboboxSale() {
        ArrayList<LOAI> listL = (ArrayList<LOAI>) getAllLoai();
        for (LOAI l : listL) {
            // System.out.println(l.getMaLoai());
            comboboxSP.addItem(l.getMaLoai());
        }
    }

    //hàm này dùng để đổ dữ liệu và combox nhân viên ở form bán hàng
    public void fillComboboxSaleNV() {
        ArrayList<NHANVIEN> listL = (ArrayList<NHANVIEN>) getAllNV();
        for (NHANVIEN l : listL) {
            // System.out.println(l.getMaLoai());
            ComboxNhanVien.addItem(l.getMaNV().trim());
        }
    }

    //hàm này dùng để tìm kiếm
    public void sreachSPForForm(String s) {
        listSearch.clear();
        ArrayList<SANPHAM> listSP = (ArrayList<SANPHAM>) getAllSP();
        if (fieldSearch.getText().isEmpty() == false) {
            listSearch = searchProduct((ArrayList<SANPHAM>) listSP, s.trim());
        }
    }

    //hàm này điền dữ liệu vào bảng sau khi search
    public void fillTableForFormProductSearch() {
        DefaultTableModel model = (DefaultTableModel) tableSP_formSP.getModel();
        model.setRowCount(0);
        for (SANPHAM sp : listSearch) {
            //System.out.println(sp.getMaSP());
            Object[] row = new Object[]{sp.getMaSP(), sp.getTen_SP(), sp.getMaLoai(), sp.getGia(), sp.getSoLuongConLai(), sp.getDon_vi_tinh()};
            model.addRow(row);
        }
    }

    //hàm này dùng để nhấn click vào bảng là nó hiện thông tin lên field
    public void showDetail() {
        //System.out.println(listSearch.get(0).getMaSP() + ";lm;la;lm;lfms;lmf;smf;lsfs");
        if (listSearch.isEmpty()) {
            ArrayList<SANPHAM> listSP = (ArrayList<SANPHAM>) getAllSP();
            int index = tableSP_formSP.getSelectedRow();
            SANPHAM sp = listSP.get(index);
            show(sp);
        } else {

            int index = tableSP_formSP.getSelectedRow();
            SANPHAM sp = listSearch.get(index);
            show(sp);
        }

    }

    //hàm này dùng để điền dữ liệu vào field
    public void show(SANPHAM sp) {
        fieldTenSP.setText(sp.getTen_SP());
        fieldMaSP.setText(sp.getMaSP());
        fieldDonViTinh.setText(sp.getDon_vi_tinh());
        fieldDonGia.setText(Double.toString(sp.getGia()));
        fieldSoLuong.setText(Integer.toString(sp.getSoLuongConLai()));
        for (int i = 0; i < comboBoxLoai.getItemCount(); i++) {
            if (sp.getMaLoai().equals(comboBoxLoai.getItemAt(i))) {
                comboBoxLoai.setSelectedIndex(i);
                break;
            }
        }
    }

    //hàm này dùng để thêm data vào danh sách
    public void addSPForForm() {
        SANPHAM sp = new SANPHAM();
        if (fieldMaSP.getText().trim().isEmpty() == false && fieldDonViTinh.getText().trim().isEmpty() == false && fieldTenSP.getText().trim().isEmpty() == false && fieldSoLuong.getText().trim().isEmpty() == false && fieldDonGia.getText().trim().isEmpty() == false && exist(fieldMaSP.getText()) == false) {
            sp.setMaSP(fieldMaSP.getText());
            sp.setTen_SP(fieldTenSP.getText());
            sp.setDon_vi_tinh(fieldDonViTinh.getText());
            sp.setGia(Double.parseDouble(fieldDonGia.getText()));
            sp.setSoLuongConLai(Integer.parseInt(fieldSoLuong.getText()));
            sp.setMaLoai((String) comboBoxLoai.getSelectedItem());
            addSP(sp);

        } else if (exist(fieldMaSP.getText()) == true) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
        } else {
            JOptionPane.showMessageDialog(this, "Bạn phải điền đẩy đủ thông tin");
        }

    }

    //hàm này dùng để xem maSP có tồn tại hay là không
    public boolean exist(String s) {
        ArrayList<SANPHAM> listSP = (ArrayList<SANPHAM>) getAllSP();
        for (SANPHAM sp : listSP) {
            if (sp.getMaSP().trim().equals(s.toLowerCase().trim())) {
                return true;
            }
        }
        return false;
    }

    //hàm này dùng để cập nhật lại sản phẩm
    public void updateSPForProduct() {
        SANPHAM sp = new SANPHAM();
        ArrayList<SANPHAM> listSP = (ArrayList<SANPHAM>) getAllSP();
        int index = tableSP_formSP.getSelectedRow();
        if (fieldMaSP.getText().trim().isEmpty() == false) {
            SANPHAM sp1;
            if (listSearch.isEmpty()) {
                sp1 = listSP.get(index);
            } else {
                sp1 = listSearch.get(index);
            }
            sp.setMaSP(fieldMaSP.getText());
            sp.setTen_SP(fieldTenSP.getText());
            sp.setDon_vi_tinh(fieldDonViTinh.getText());
            sp.setGia(Double.parseDouble(fieldDonGia.getText()));
            sp.setSoLuongConLai(Integer.parseInt(fieldSoLuong.getText()));
            sp.setMaLoai((String) comboBoxLoai.getSelectedItem());
            updateSP(sp, sp1.getMaSP().trim());

        }
    }

    //hàm này dùng đề xóa sản phẩm
    public void deleteSPForProduct() {
        SANPHAM sp = new SANPHAM();
        ArrayList<SANPHAM> listSP = (ArrayList<SANPHAM>) getAllSP();
        int index = tableSP_formSP.getSelectedRow();
        //SANPHAM sp1 = listSP.get(index);
        SANPHAM sp1;
        if (listSearch.isEmpty()) {
            sp1 = listSP.get(index);
        } else {
            sp1 = listSearch.get(index);
        }
        deleteSP(sp1.getMaSP().trim());
    }

    //khi nhập file exel thì tự động cập nhật hoặc là thêm
    public void autoUpdate(String filePath) throws IOException {
        ArrayList<SANPHAM> listSP = importExel(filePath);
        for (SANPHAM sp : listSP) {
            boolean key = exist(sp.getMaSP());
            if (key == false) {
                addSP(sp);
            } else {
                updateSP(sp, sp.getMaSP().trim());
            }

        }
    }

    //////////////////////////////////////////////////////////các hàm cho form nhân viên
    public void fillTableForFormNV() {
        ArrayList<NHANVIEN> listSP1 = (ArrayList<NHANVIEN>) getAllNV();
        DefaultTableModel model = (DefaultTableModel) tableNV_formNV.getModel();
        model.setRowCount(0);
        for (NHANVIEN sp : listSP1) {
            //System.out.println(sp.getMaSP());
            Object[] row = new Object[]{sp.getMaNV(), sp.getTenNV(), sp.getGioiTinh(), sp.getNgaySinh()};
            model.addRow(row);
        }
    }

    //hàm này dùng để nhấn click vào bảng là nó hiện thông tin lên field
    public void showDetailNV() {
        ArrayList<NHANVIEN> listSP = (ArrayList<NHANVIEN>) getAllNV();
        int index = tableNV_formNV.getSelectedRow();
        if (listSearchNV.isEmpty()) {
            NHANVIEN sp = listSP.get(index);
            showNV(sp);
        } else {
            NHANVIEN sp = listSearchNV.get(index);
            showNV(sp);
        }

    }

    //hàm này dùng để điền dữ liệu vào field
    public void showNV(NHANVIEN sp) {
        fieldMaNV.setText(sp.getMaNV());
        fieldTenNV.setText(sp.getTenNV());
        fieldSex.setText(sp.getGioiTinh());
        String date = new SimpleDateFormat("dd/MM/yyyy").format(sp.getNgaySinh());
        fieldNamSinh.setText(date);

    }

    //hàm này dùng để thêm data vào danh sách
    public void addNVForForm() throws ParseException {
        NHANVIEN sp = new NHANVIEN();
        if (fieldSex.getText().trim().isEmpty() == false && fieldNamSinh.getText().trim().isEmpty() == false && fieldTenNV.getText().trim().isEmpty() == false && fieldMaNV.getText().trim().isEmpty() == false && existNV(fieldMaNV.getText()) == false) {
            sp.setMaNV(fieldMaNV.getText());
            sp.setTenNV(fieldTenNV.getText());
            sp.setGioiTinh(fieldSex.getText());
            sp.setNgaySinh(fieldNamSinh.getText());
            addNV(sp);

        } else if (existNV(fieldMaNV.getText()) == true) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại");
        } else {
            JOptionPane.showMessageDialog(this, "bạn phải điền đầy đủ thông tin trước khi thêm sản phẩm");
        }

    }

    //hàm này dùng để xem maSP có tồn tại hay là không
    public boolean existNV(String s) {
        ArrayList<NHANVIEN> listSP = (ArrayList<NHANVIEN>) getAllNV();
        for (NHANVIEN sp : listSP) {
            if (sp.getMaNV().trim().equals(s.toLowerCase().trim())) {
                return true;
            }
        }
        return false;
    }

    //hàm này dùng để tìm kiếm
    public void sreachNVForForm() {
        listSearchNV.clear();
        ArrayList<NHANVIEN> listSP = (ArrayList<NHANVIEN>) getAllNV();
        if (fieldSearchNV.getText().isEmpty() == false) {
            listSearchNV = searchNV((ArrayList<NHANVIEN>) listSP, keySearch);
        }
    }

    //hàm này dùng đề hiển thị lên bảng sau khi tìm kiếm
    public void fillTableForFormNVSearch() {
        DefaultTableModel model = (DefaultTableModel) tableNV_formNV.getModel();
        model.setRowCount(0);
        for (NHANVIEN sp : listSearchNV) {
            //System.out.println(sp.getMaSP());
            Object[] row = new Object[]{sp.getMaNV(), sp.getTenNV(), sp.getGioiTinh(), sp.getNgaySinh()};
            model.addRow(row);
        }
    }

    //hàm này dùng để cập nhật lại sản phẩm
    public void updateNVForForm() throws ParseException {
        NHANVIEN sp = new NHANVIEN();
        ArrayList<NHANVIEN> listSP = (ArrayList<NHANVIEN>) getAllNV();
        int index = tableNV_formNV.getSelectedRow();
        if (fieldMaNV.getText().trim().isEmpty() == false) {
            NHANVIEN sp1;
            if (listSearchNV.isEmpty()) {
                sp1 = listSP.get(index);
            } else {
                sp1 = listSearchNV.get(index);
            }
            sp.setMaNV(fieldMaNV.getText());
            sp.setTenNV(fieldTenNV.getText());
            sp.setGioiTinh(fieldSex.getText());
            sp.setNgaySinh(fieldNamSinh.getText());
            updateNV(sp, sp1.getMaNV().trim());

        }
    }

    //hàm này dùng đề xóa sản phẩm
    public void deleteNVForProduct() {
        NHANVIEN sp = new NHANVIEN();
        ArrayList<NHANVIEN> listSP = (ArrayList<NHANVIEN>) getAllNV();
        int index = tableNV_formNV.getSelectedRow();
        //NHANVIEN sp1 = listSP.get(index);
        NHANVIEN sp1;
        if (listSearchNV.isEmpty()) {
            sp1 = listSP.get(index);
        } else {
            sp1 = listSearchNV.get(index);
        }
        deleteNV(sp1.getMaNV().trim());
    }

    //khi nhập file exel thì tự động cập nhật hoặc là thêm
    public void autoUpdateNV(String filePath) throws IOException, FileNotFoundException, ParseException {
        ArrayList<NHANVIEN> listSP = importExelNV(filePath);
        for (NHANVIEN sp : listSP) {
            boolean key = existNV(sp.getMaNV());
            if (key == false) {
                addNV(sp);
            } else {
                updateNV(sp, sp.getMaNV().trim());
            }

        }
    }

    ///////////////////////////////////////////////////NHẬP HÀNG
    //hàm đẩy dữ liệu lên table nhập hàng
    public void fillTableKhoHang() {
        ArrayList<SANPHAM> listSP1 = (ArrayList<SANPHAM>) getAllSP();
        DefaultTableModel model = (DefaultTableModel) tblKhoHang.getModel();
        model.setRowCount(0);
        for (SANPHAM sp : listSP1) {
            //System.out.println(sp.getMaSP());
            Object[] row = new Object[]{sp.getMaSP(), sp.getTen_SP(), sp.getSoLuongConLai()};
            model.addRow(row);
        }
    }

    //hàm này dùng để đổ dữ liệu khi đã tìm kiếm xong
    public void fillTableKhoHangSearch() {
        DefaultTableModel model = (DefaultTableModel) tblKhoHang.getModel();
        model.setRowCount(0);
        for (SANPHAM sp : listSearch1) {
            Object[] row = new Object[]{sp.getMaSP(), sp.getTen_SP(), sp.getSoLuongConLai()};
            model.addRow(row);
        }
    }

    //hàm này dùng để tìm kiếm
    public void sreachSPForFormNhapHang(String s) {
        listSearch1.clear();
        ArrayList<SANPHAM> listSP = (ArrayList<SANPHAM>) getAllSP();
        if (fieldSearchKhoHang.getText().isEmpty() == false) {
            listSearch1 = searchProduct((ArrayList<SANPHAM>) listSP, s.trim());
        }
    }

    //hàm này dùng để nhấn click vào bảng là nó hiện thông tin lên field
    public void showDetailKhoHang() {
        ArrayList<SANPHAM> listSP = (ArrayList<SANPHAM>) getAllSP();
        int index = tblKhoHang.getSelectedRow();
        SANPHAM sp;
        if (listSearch1.isEmpty()) {
            sp = listSP.get(index);
            showKhoHang(sp);
        } else {
            sp = listSearch1.get(index);
            showKhoHang(sp);
        }

    }

    //hàm này dùng để điền dữ liệu vào field
    public void showKhoHang(SANPHAM sp) {
        fieldMaSPForNhapHang.setText(sp.getMaSP());
        fieldTenSPForNhapHang.setText(sp.getTen_SP());

    }

    //hàm này dùng để đổ dữ liệu vào combobox trong form sản phẩm
    public void fillComboboxKhoHang() {
        ArrayList<NHACUNGCAP> listL = (ArrayList<NHACUNGCAP>) getAllNCC();
        for (NHACUNGCAP l : listL) {
            fieldNCCForNhapHang.addItem(l.getMaNCC());
        }
    }

    //hàm đẩy dữ liệu lên table nhập hàng
    public void fillTableNhapHang() {
        DefaultTableModel model = (DefaultTableModel) tblNhapHang.getModel();
        model.setRowCount(0);
        for (PHIEUNHAP sp : listNH) {
            Object[] row = new Object[]{sp.getMaSP(), getSPKey(sp.getMaSP()), sp.getSoLuongNhap(), sp.getGiaCungCap(), sp.getMaNCC(), sp.getThanhTien()};
            model.addRow(row);
        }
    }

    //hàm xóa dữ liệu trên list tạm nhập hàng lists nh
    public void deleteNHForForm() {
        int index = tblNhapHang.getSelectedRow();
        listNH.remove(index);
    }

    //hàm này dùng để xem mã ncc với maSP có tồn tại chưa
    public boolean existNH(String ncc, String MaSP) {
        for (PHIEUNHAP sp : listNH) {
            if (sp.getMaNCC().trim().equals(ncc.trim())) {
                if (sp.getMaSP().trim().equals(MaSP.trim())) {
                    return true;
                }

            }
        }
        return false;
    }

    //hàm này đẩy toàn bộ dữ liệu từ biến tạm vào 
    public void addAllNH() {
        for (PHIEUNHAP pn : listNH) {
            //System.out.println(pn.getMaPN() + "  " + pn.getMaNCC() +"  "+ pn.getMaSP() +" "+ pn.getGiaCungCap() + " " +pn.getSoLuongNhap() + " "+ pn.getThanhTien());
            addPN(pn);
        }
    }

    //hàm này dùng để tự động udate số lượng tồn kho 
    public void autoUpdateKhoHang() {
        ArrayList<SANPHAM> listSP1 = (ArrayList<SANPHAM>) getAllSP();
        for (PHIEUNHAP pn : listNH) {
            for (SANPHAM sp : listSP1) {
                if (pn.getMaSP().trim().equals(sp.getMaSP().trim())) {
                    int tongSP = sp.getSoLuongConLai() + pn.getSoLuongNhap();
                    sp.setSoLuongConLai(tongSP);
                    updateSP(sp, sp.getMaSP());
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////chi tiết phiếu nhập
    //hàm đẩy dữ liệu lên table chi tiết phiếu nhập
    public void fillTableCTPN(String mapn) {
        ArrayList<PHIEUNHAP> listPN = (ArrayList<PHIEUNHAP>) getAllPNKey(mapn.trim());
        DefaultTableModel model = (DefaultTableModel) tblCTPN.getModel();
        model.setRowCount(0);
        for (PHIEUNHAP sp : listPN) {
            //System.out.println(sp.getMaSP());
            Object[] row = new Object[]{sp.getMaNCC(), sp.getMaSP(), sp.getGiaCungCap(), sp.getSoLuongNhap(), sp.getThanhTien()};
            model.addRow(row);
        }
        PHIEUNHAP sp = listPN.get(0);
        maPN_CTPN.setText(sp.getMaPN());
        String s5 = new SimpleDateFormat("dd/MM/yyyy").format(sp.getNgayNhap());
        ngaynhap_CTPN.setText(s5);
        double tongtien = 0;
        for (PHIEUNHAP pn1 : listPN) {
            tongtien = tongtien + pn1.getThanhTien();
        }
        tongTien_CTPN.setText(String.valueOf(tongtien));
    }

    //hàm này dùng để nhấn click vào bảng là nó hiện thông tin lên field
    public void showDetailPN() {

        ArrayList<PHIEUNHAP> listPN = (ArrayList<PHIEUNHAP>) getAllPNKey(maPN_CTPN.getText().trim());
        int index = tblCTPN.getSelectedRow();
        PHIEUNHAP sp = listPN.get(index);
        showPN(sp);

    }

    //hàm này dùng để điền dữ liệu vào field
    public void showPN(PHIEUNHAP sp) {
        fieldMaNCCForPN.setText(sp.getMaNCC());
        fieldMaSPForPN.setText(sp.getMaSP());
        fieldGiaForPN.setText(String.valueOf(sp.getGiaCungCap()));
        fieldSoLuongForPN.setText(String.valueOf(sp.getSoLuongNhap()));

    }

    //hàm này dùng để xem maSp , mã nhà cung cấp , mã phiếu nhập có tồn tại hay là không
    public boolean existPN(String s1, String s2, String s3) {
        ArrayList<PHIEUNHAP> listSP = (ArrayList<PHIEUNHAP>) getAllPN();
        for (PHIEUNHAP sp : listSP) {
            if (sp.getMaPN().trim().equals(s1.trim())) {
                if (sp.getMaNCC().trim().equals(s2.trim())) {
                    if (sp.getMaSP().trim().equals(s3.trim())) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    //hàm này dùng để thêm data vào danh sách
    public void addPNForForm() throws ParseException {
        String pn = maPN_CTPN.getText();
        String NgayNhap = ngaynhap_CTPN.getText();
        String tongTien = tongTien_CTPN.getText();
        String MaNCC = fieldMaNCCForPN.getText();
        String MaSP = fieldMaSPForPN.getText();
        String gia = fieldGiaForPN.getText();
        String soluong = fieldSoLuongForPN.getText();
        //System.out.println(pn + "  " + MaNCC +   "   " +MaSP );
        PHIEUNHAP sp = new PHIEUNHAP();
        //System.out.println(existPN(pn,MaNCC,MaSP));
        if (MaNCC.trim().isEmpty() == false && MaSP.trim().isEmpty() == false && gia.trim().isEmpty() == false && soluong.trim().isEmpty() == false && existPN(pn, MaNCC, MaSP) == false && exist(MaSP) == true && existNCC(MaNCC) == true) {
            ArrayList<SANPHAM> listSP1 = (ArrayList<SANPHAM>) getAllSP();
            sp.setMaPN(pn);
            sp.setMaNCC(MaNCC);
            sp.setMaSP(MaSP);
            sp.setGiaCungCap(Double.parseDouble(gia));
            sp.setSoLuongNhap(Integer.parseInt(soluong));
            sp.setNgayNhap(NgayNhap);
            sp.setThanhTien(Double.parseDouble(tongTien));
            addPN(sp);
            for (SANPHAM sp1 : listSP1) {
                if (sp.getMaSP().trim().equals(sp1.getMaSP().trim())) {
                    int tong = sp.getSoLuongNhap() + sp1.getSoLuongConLai();
                    sp1.setSoLuongConLai(tong);
                    updateSP(sp1, sp.getMaSP());
                }
            }

        } else if (MaNCC.trim().isEmpty() == false && MaSP.trim().isEmpty() == false && gia.trim().isEmpty() == false && soluong.trim().isEmpty() == false && existPN(pn, MaNCC, MaSP) == true && exist(MaSP) == true && existNCC(MaNCC) == true) {
            JOptionPane.showMessageDialog(rootPane, "Nhà cung cấp đã cung cấp sản phẩm này rồi  ", "  ", JOptionPane.INFORMATION_MESSAGE);
        } else if (MaNCC.trim().isEmpty() == false && MaSP.trim().isEmpty() == false && gia.trim().isEmpty() == false && soluong.trim().isEmpty() == false && existPN(pn, MaNCC, MaSP) == false && exist(MaSP) == false && existNCC(MaNCC) == true) {
            JOptionPane.showMessageDialog(rootPane, "Mã sản phẩm không tồn tại  ", "  ", JOptionPane.INFORMATION_MESSAGE);
        } else if (MaNCC.trim().isEmpty() == false && MaSP.trim().isEmpty() == false && gia.trim().isEmpty() == false && soluong.trim().isEmpty() == false && existPN(pn, MaNCC, MaSP) == false && exist(MaSP) == true && existNCC(MaNCC) == false) {
            JOptionPane.showMessageDialog(rootPane, "Mã nhà cung cấp không tồn tại  ", "  ", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống thông tin  ", "  ", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    //hàm này dùng kiểm tra xem mã nhà cc đã tồn tại hay chưa
    public boolean existNCC(String key) {
        ArrayList<NHACUNGCAP> listNCC = (ArrayList<NHACUNGCAP>) getAllNCC();
        for (NHACUNGCAP ncc : listNCC) {
            if (key.trim().equals(ncc.getMaNCC().trim())) {
                return true;
            }
        }
        return false;
    }

    //hàm này dùng đề xóa phiếu nhập
    public void deletePNForForm() {
        PHIEUNHAP sp = new PHIEUNHAP();
        ArrayList<PHIEUNHAP> listSP = (ArrayList<PHIEUNHAP>) getAllPNKey(maPN_CTPN.getText());
        int index = tblCTPN.getSelectedRow();
        PHIEUNHAP pn = listSP.get(index);
        deletePN(pn.getMaPN(), pn.getMaNCC(), pn.getMaSP());
        ArrayList<SANPHAM> listSP2 = (ArrayList<SANPHAM>) getAllSP();
        for (SANPHAM sp2 : listSP2) {
            if (pn.getMaSP().trim().equals(sp2.getMaSP().trim())) {
                int tong = sp2.getSoLuongConLai() - pn.getSoLuongNhap();
                sp2.setSoLuongConLai(tong);
                updateSP(sp2, sp2.getMaSP());
            }
        }
    }

    //hàm này dùng để chỉnh sửa
    public void updatePNForForm() throws ParseException {
        PHIEUNHAP pn = new PHIEUNHAP();
        ArrayList<PHIEUNHAP> listPN = (ArrayList<PHIEUNHAP>) getAllPNKey(maPN_CTPN.getText());
        int index = tblCTPN.getSelectedRow();
        if (fieldMaNCCForPN.getText().trim().isEmpty() == false & fieldMaSPForPN.getText().trim().isEmpty() == false & fieldGiaForPN.getText().trim().isEmpty() == false & fieldSoLuongForPN.getText().trim().isEmpty() == false) {
            PHIEUNHAP pn1 = listPN.get(index);
            pn.setMaNCC(fieldMaNCCForPN.getText());
            pn.setMaSP(fieldMaSPForPN.getText());
            pn.setGiaCungCap(Double.parseDouble(fieldGiaForPN.getText()));
            pn.setSoLuongNhap(Integer.parseInt(fieldSoLuongForPN.getText()));
            updatePN(pn, pn1.getMaPN(), pn1.getMaNCC(), pn1.getMaSP());
            ArrayList<SANPHAM> listSP2 = (ArrayList<SANPHAM>) getAllSP();
            for (SANPHAM sp2 : listSP2) {
                if (pn.getMaSP().trim().equals(sp2.getMaSP().trim())) {
                    int tong = (pn.getSoLuongNhap() - pn1.getSoLuongNhap()) + sp2.getSoLuongConLai();
                    sp2.setSoLuongConLai(tong);
                    updateSP(sp2, sp2.getMaSP());
                }
            }

        }
    }

    //hàm này để đẩy dữ liệu lên bảng mã pn
    public void fillTableCMaPN() {
        ArrayList<PHIEUNHAP> listPN = (ArrayList<PHIEUNHAP>) getAllPNDist();
        DefaultTableModel model = (DefaultTableModel) tblMaPN.getModel();
        model.setRowCount(0);
        for (PHIEUNHAP sp : listPN) {
            //System.out.println(sp.getMaSP());
            String date = new SimpleDateFormat("dd/MM/yyyy").format(sp.getNgayNhap());
            Object[] row = new Object[]{sp.getMaPN(), date};
            model.addRow(row);
        }
    }

    //hàm này dùng để nhấn click vào bảng là nó sẽ đẩy dữ liệu qua bảng kia
    public void showDetailPNTbl() {
        ArrayList<PHIEUNHAP> listPN = (ArrayList<PHIEUNHAP>) getAllPNDist();
        int index = tblMaPN.getSelectedRow();
        PHIEUNHAP sp;
        if (listPNSearch.isEmpty()) {
            sp = listPN.get(index);
            fillTableCTPN(sp.getMaPN());
        } else {
            sp = listPNSearch.get(index);
            fillTableCTPN(sp.getMaPN());
        }

    }

    //hàm này tìm kiếm theo ngày cho form
    public void fillTableCMaPNSearch() {
        listPNSearch = (ArrayList<PHIEUNHAP>) getAllPNDistSearch(fieldSearchCTPN.getText());
        DefaultTableModel model = (DefaultTableModel) tblMaPN.getModel();
        model.setRowCount(0);
        for (PHIEUNHAP sp : listPNSearch) {
            //System.out.println(sp.getMaSP());
            String date = new SimpleDateFormat("dd/MM/yyyy").format(sp.getNgayNhap());
            Object[] row = new Object[]{sp.getMaPN(), date};
            model.addRow(row);
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////// code by tuan vu 
    /////////////////////////////////////////////////////// Khuyến mãi
    //hàm này dùng để điền dữ liệu từ database lên table form
    public void fillTableForFormKM() {
        ArrayList<KHUYENMAI> listKM1 = (ArrayList<KHUYENMAI>) getAllKM();
        DefaultTableModel model = (DefaultTableModel) tableMaKM.getModel();
        model.setRowCount(0);
        for (KHUYENMAI km : listKM1) {
            //System.out.println(km.getMaKM());
            Object[] row = new Object[]{km.getMaKM(), km.getTenKM(), km.getPhanTramKM(), km.getDieuKienKM(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getTinhTrang()};
            model.addRow(row);
        }
    }

    //hàm này dùng để nhấn click vào bảng khuyeN MAI là nó hiện thông tin lên field
    public void showDetailKM() throws ParseException {
        ArrayList<KHUYENMAI> listKM = (ArrayList<KHUYENMAI>) getAllKM();
        int index = tableMaKM.getSelectedRow();
        KHUYENMAI km = listKM.get(index);
        showKM(km);

    }
    //hàm này dùng để điền dữ liệu vào field

    public void showKM(KHUYENMAI km) throws ParseException {
        txtMaKM.setText(km.getMaKM());
        txtTenKM.setText(km.getTenKM());
        txtPhanTramKM.setText(Double.toString(km.getPhanTramKM()));
        txtDieuKien.setText(Double.toString(km.getDieuKienKM()));
        txtTinhTrangKM.setText(km.getTinhTrang());
        String dateN1 = new SimpleDateFormat("dd/MM/yyyy").format(km.getNgayBatDau());
        Date1.setText(dateN1);
        String dateN2 = new SimpleDateFormat("dd/MM/yyyy").format(km.getNgayKetThuc());
        Date2.setText(dateN2);

    }

    //hàm này dùng để thêm data vào danh sách
    public void addKMForForm() throws ParseException {
        KHUYENMAI km = new KHUYENMAI();
        if (txtMaKM.getText().trim().isEmpty() == false & existKM(txtMaKM.getText()) == false) {
            km.setMaKM(txtMaKM.getText());
            km.setTenKM(txtTenKM.getText());
            km.setPhanTramKM(Double.valueOf(txtPhanTramKM.getText()));
            km.setDieuKienKM(Double.valueOf(txtDieuKien.getText()));
            km.setTinhTrang(txtTinhTrangKM.getText());
            km.setNgayBatDau(Date1.getText());
            km.setNgayKetThuc(Date2.getText());
            if (km.getNgayBatDau().before(km.getNgayKetThuc())) {
                addKM(km);
            } else {

                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không hợp lệ!", "check Khuyen mai", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    //hàm này dùng để xem maKM có tồn tại hay là không
    public boolean existKM(String s) {
        ArrayList<KHUYENMAI> listKM = (ArrayList<KHUYENMAI>) getAllKM();
        for (KHUYENMAI km : listKM) {
            if (km.getMaKM().trim().equals(s.toLowerCase().trim())) {
                return true;
            }
        }
        return false;
    }

    //hàm này dùng để xóa
    public void deleteKMForForm() {
        KHUYENMAI km = new KHUYENMAI();
        ArrayList<KHUYENMAI> listKM = (ArrayList<KHUYENMAI>) getAllKM();
        int index = tableMaKM.getSelectedRow();
        KHUYENMAI km1 = listKM.get(index);
        deleteKM(km1.getMaKM().trim());
    }

    // hàm này dùng để sửa lại thông tin 1 Khuyến mai
    public void updateKMForForm() throws ParseException {
        KHUYENMAI km = new KHUYENMAI();
        ArrayList<KHUYENMAI> listKM = (ArrayList<KHUYENMAI>) getAllKM();
        int index = tableMaKM.getSelectedRow();
        if (txtMaKM.getText().trim().isEmpty() == false) {
            KHUYENMAI km1 = listKM.get(index);
            km.setMaKM(txtMaKM.getText());
            km.setTenKM(txtTenKM.getText());
            km.setPhanTramKM(Double.valueOf(txtPhanTramKM.getText()));
            km.setDieuKienKM(Double.valueOf(txtDieuKien.getText()));
            km.setTinhTrang(txtTinhTrangKM.getText());
            km.setNgayBatDau(Date1.getText());
            km.setNgayKetThuc(Date2.getText());
            if (km.getNgayBatDau().before(km.getNgayKetThuc())) {
                updateKM(km, km1.getMaKM().trim());
            } else {

                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không hợp lệ!", "check Khuyen mai", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    //hàm reset
    private void resetFormKM() {
        txtMaKM.setText("");
        txtTenKM.setText("");
        txtPhanTramKM.setText("");
        txtDieuKien.setText("");
        Date1.setText("");
        Date2.setText("");
        txtTinhTrangKM.setText("");

        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        fillTableForFormKM();
    }

    //////////////////////////////////////////////////////////////////// khách hàng
    //hàm này đổ dữ liệu vào bảng khách hàng
    public void fillTableForFormKH() {
        ArrayList<KHACHHANG> listKH1 = (ArrayList<KHACHHANG>) getAllKH();
        DefaultTableModel model = (DefaultTableModel) tableKH.getModel();
        model.setRowCount(0);
        for (KHACHHANG kh : listKH1) {
            //System.out.println(sp.getMaSP());
            Object[] row = new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh(), kh.getTongChiTieu()};
            model.addRow(row);
        }
    }

    // hàm này sử dụng khi click vào tableKH nó sẽ hiện thông tin lên Field
    public void showDetailKH() {
        ArrayList<KHACHHANG> listKH = (ArrayList<KHACHHANG>) getAllKH();
        int index = tableKH.getSelectedRow();
        if (listSeachKH.isEmpty() && listSeachKH1.isEmpty()) {
            KHACHHANG kh = listKH.get(index);
            showKH(kh);
        } else if (listSeachKH.isEmpty() == false && listSeachKH1.isEmpty() == true) {
            KHACHHANG kh = listSeachKH.get(index);
            showKH(kh);
        } else {
            KHACHHANG kh = listSeachKH1.get(index);
            showKH(kh);
        }

    }

    //ham này dùng đổ dự liệu vào field
    public void showKH(KHACHHANG kh) {
        txtMaKH.setText(kh.getMaKH());
        txtTenKH.setText(kh.getTenKH());
        txtTongChiTieu.setText(Double.toString(kh.getTongChiTieu()));
        // cbxGioiTinh.setSelectedItem(kh.getGioiTinh());
        for (int i = 0; i < cbxGioiTinh.getItemCount(); i++) {
            if (kh.getGioiTinh().equals(cbxGioiTinh.getItemAt(i))) {
                cbxGioiTinh.setSelectedIndex(i);
                break;
            }
        }

    }

    //ham nay dung de them dâta vao danh sach
    public void addKHForForm() throws ParseException {
        KHACHHANG kh = new KHACHHANG();
        if (txtMaKH.getText().trim().isEmpty() == false & existKH(txtMaKH.getText()) == false) {
            kh.setMaKH(txtMaKH.getText());
            kh.setTenKH(txtTenKH.getText());
            kh.setTongChiTieu(Double.parseDouble(txtTongChiTieu.getText()));
            kh.setGioiTinh((String) cbxGioiTinh.getSelectedItem());
            addKH(kh);
        } else if (existKH(txtMaKH.getText()) == true) {
            JOptionPane.showMessageDialog(this, "mã khach hang đã tồn tại");

        } else {
            JOptionPane.showMessageDialog(this, "bạn phải điền đầy đủ thông tin trước khi thêm khách hàng !");
        }

    }

    //ham này dùng dể kiểm tra mẫ KH có tồn tại không
    public boolean existKH(String s) {
        ArrayList<KHACHHANG> listKH = (ArrayList<KHACHHANG>) getAllKH();
        for (KHACHHANG kh : listKH) {
            if (kh.getMaKH().trim().equals(s.toLowerCase().trim())) {
                return true;
            }
        }
        return false;
    }

    ////////////hàm dùng để tim kiem khach hang  
    public void sreachKHForFormName() {
        listSeachKH1.clear();
        listSeachKH.clear();
        ArrayList<KHACHHANG> listKH = (ArrayList<KHACHHANG>) getAllKH();
        if (txtTuKhoaTim.getText().isEmpty() == false) {
            listSeachKH1 = searchKH((ArrayList<KHACHHANG>) listKH, keySearchKH);
        }

    }

    //hàm này điền dữ liệu vào bảng sau khi search
    public void fillTableForFormKHSearchName() {
        DefaultTableModel model = (DefaultTableModel) tableKH.getModel();
        model.setRowCount(0);
        for (KHACHHANG kh : listSeachKH1) {
            Object[] row = new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh(), kh.getTongChiTieu()};
            model.addRow(row);
        }
    }

    //////////hàm này dùng để đẩy dữ liệu vào list toàn cục sau khi tìm kiếm
    public void sreachKHCTForForm() {
        listSeachKH1.clear();
        listSeachKH.clear();
        ArrayList<KHACHHANG> listSP = (ArrayList<KHACHHANG>) getAllKH();
        listSeachKH = searchKHChiTieu((ArrayList<KHACHHANG>) listSP, min, max);

    }

    //hàm này điền dữ liệu vào bảng sau khi search
    public void fillTableForFormKHSearch() {
        DefaultTableModel model = (DefaultTableModel) tableKH.getModel();
        model.setRowCount(0);
        for (KHACHHANG kh : listSeachKH) {
            //(kh.getMaKH() + "                     kmkljlklkkl    ọkljoljjhjhjh");
            Object[] row = new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh(), kh.getTongChiTieu()};
            model.addRow(row);
        }
    }

    //hàm này dùng đẻ chỉnh sủa KH
    public void updateKHForForm() throws ParseException {
        KHACHHANG kh = new KHACHHANG();
        ArrayList<KHACHHANG> listKH = (ArrayList<KHACHHANG>) getAllKH();
        int index = tableKH.getSelectedRow();
        if (txtMaKH.getText().trim().isEmpty() == false) {
            KHACHHANG kh1;
            if (listSeachKH.isEmpty() && listSeachKH1.isEmpty()) {
                kh1 = listKH.get(index);
            } else if (listSeachKH.isEmpty() == false && listSeachKH1.isEmpty() == true) {
                kh1 = listSeachKH.get(index);
            } else {
                kh1 = listSeachKH1.get(index);
            }
            kh.setMaKH(txtMaKH.getText());
            kh.setTenKH(txtTenKH.getText());
            kh.setTongChiTieu(Double.parseDouble(txtTongChiTieu.getText()));
            kh.setGioiTinh((String) cbxGioiTinh.getSelectedItem());
            updateKH(kh, kh1.getMaKH().trim());

        }
    }

    //hàm này đung xóa khach 
    public void deleteKHForForm() {
        KHACHHANG kh = new KHACHHANG();
        ArrayList<KHACHHANG> listKH = (ArrayList<KHACHHANG>) getAllKH();
        int index = tableKH.getSelectedRow();
        // KHACHHANG kh1 = listKH.get(index);
        KHACHHANG kh1;
        if (listSeachKH.isEmpty() && listSeachKH1.isEmpty()) {
            kh1 = listKH.get(index);
        } else if (listSeachKH.isEmpty() == false && listSeachKH1.isEmpty() == true) {
            kh1 = listSeachKH.get(index);
        } else {
            kh1 = listSeachKH1.get(index);
        }

        deleteKH(kh1.getMaKH().trim());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboxNhanVien;
    private javax.swing.JTextField Date1;
    private javax.swing.JTextField Date2;
    private javax.swing.JLabel P4;
    private javax.swing.JLabel P5;
    private javax.swing.JLabel P6;
    private javax.swing.JLabel P7;
    private javax.swing.JLabel P8;
    private javax.swing.JPanel SanPham;
    private javax.swing.JScrollPane area;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddBag;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFind1;
    private javax.swing.JButton btnLoading;
    private javax.swing.JButton btnLoadingKH;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchCTPH;
    private javax.swing.JButton btnSearchKhoHang;
    private javax.swing.JButton btnSetup;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaPN;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaNhap;
    private javax.swing.JButton btnXoaPN;
    private javax.swing.JButton btnXuat;
    private javax.swing.JButton buttom_clear;
    private javax.swing.JButton buttom_clearNV;
    private javax.swing.JButton buttom_save;
    private javax.swing.JButton buttom_saveNV;
    private javax.swing.JButton buttom_them;
    private javax.swing.JButton buttom_themNV;
    private javax.swing.JButton buttonDelSale;
    private javax.swing.JButton button_export;
    private javax.swing.JButton button_exportNV;
    private javax.swing.JButton button_import;
    private javax.swing.JButton button_importNV;
    private javax.swing.JButton button_refresh;
    private javax.swing.JButton button_refresh1;
    private javax.swing.JButton button_refresh2;
    private javax.swing.JButton button_refresh3;
    private javax.swing.JButton button_search;
    private javax.swing.JComboBox<String> cbxGioiTinh;
    private QuanLyPizza.ravenchart.Chart chart;
    private javax.swing.JComboBox<String> comboBoxLoai;
    private javax.swing.JComboBox<String> comboboxSP;
    private javax.swing.JButton exportBill;
    private javax.swing.JTextField fieldDonGia;
    private javax.swing.JTextField fieldDonViTinh;
    private javax.swing.JTextField fieldGiaForNhapHang;
    private javax.swing.JTextField fieldGiaForPN;
    private javax.swing.JTextField fieldMaNCCForPN;
    private javax.swing.JTextField fieldMaNV;
    private javax.swing.JTextField fieldMaSP;
    private javax.swing.JLabel fieldMaSPForNhapHang;
    private javax.swing.JTextField fieldMaSPForPN;
    private javax.swing.JComboBox<String> fieldNCCForNhapHang;
    private javax.swing.JTextField fieldNamSinh;
    private javax.swing.JTextField fieldSearch;
    private javax.swing.JTextField fieldSearchCTPN;
    private javax.swing.JTextField fieldSearchKhoHang;
    private javax.swing.JTextField fieldSearchNV;
    private javax.swing.JTextField fieldSex;
    private javax.swing.JTextField fieldSoLuong;
    private javax.swing.JTextField fieldSoLuongForNhapHang;
    private javax.swing.JTextField fieldSoLuongForPN;
    private javax.swing.JTextField fieldTenNV;
    private javax.swing.JTextField fieldTenSP;
    private javax.swing.JLabel fieldTenSPForNhapHang;
    private javax.swing.JTextField fieldTien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuBar jMenuBar7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lableMHD;
    private javax.swing.JLabel lableMKH;
    private javax.swing.JLabel labletongtien;
    private javax.swing.JPanel m1;
    private javax.swing.JPanel m3;
    private javax.swing.JPanel m4;
    private javax.swing.JPanel m6;
    private javax.swing.JPanel m7;
    private javax.swing.JLabel maPN_CTPN;
    private javax.swing.JLabel ngaynhap_CTPN;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JPanel paneNhapHang;
    private javax.swing.JPanel panleMain;
    private javax.swing.JSpinner spinerSP;
    private javax.swing.JTabbedPane tab3;
    private javax.swing.JTabbedPane tab_MENU1;
    private javax.swing.JTabbedPane tab_MENU2;
    private javax.swing.JPanel tab_banHang;
    private javax.swing.JTable tableKH;
    private javax.swing.JTable tableMaKM;
    private javax.swing.JTable tableNV_formNV;
    private javax.swing.JTable tableSP_formSP;
    private javax.swing.JTable tbCTHD;
    private javax.swing.JTable tbDSHDCHIL;
    private javax.swing.JTable tbGH;
    private javax.swing.JTable tbSP;
    private javax.swing.JTable tbTOPBAnCHAY;
    private javax.swing.JTable tblCTPN;
    private javax.swing.JTable tblKhoHang;
    private javax.swing.JTable tblMaPN;
    private javax.swing.JTable tblNhapHang;
    private javax.swing.JLabel tongTien_CTPN;
    private javax.swing.JLabel txtCoutDoanhThu;
    private javax.swing.JLabel txtCoutKH;
    private javax.swing.JLabel txtCoutMONAN;
    private javax.swing.JLabel txtCoutNV;
    private javax.swing.JTextField txtDOnGia;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    private javax.swing.JTextField txtPhanTramKM;
    private javax.swing.JTextField txtSOLUONG;
    private javax.swing.JTextField txtTENSP;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTinhTrangKM;
    private javax.swing.JTextField txtTongChiTieu;
    private javax.swing.JTextField txtTuKhoaTim;
    // End of variables declaration//GEN-END:variables

    private void showSpinnerSP() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        spinerSP.setModel(spinnerModel);
    }

}
