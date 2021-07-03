import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class Drawing extends JFrame {

    private final JPanel panel;
    private final JPanel panel2;
    private final JPanel panel3;
    //    private final JPanel panel4;
    private Shaping shaping = new Shaping();

    Color lineColor = Color.BLACK;
    Color fillColor = Color.WHITE;


    Box box, box2, box3, box4, box5, box6;
    JLabel lText = new JLabel("Lokasi Mouse");
    JLabel lStroke = new JLabel("Jenis Line");
    JLabel lTebal = new JLabel("Ketebalan");
    public JLabel lokasi = new JLabel();

    JLabel lTranslasi = new JLabel("Translasi");
    JLabel lTranslasiX = new JLabel("X : ");
    JLabel lTranslasiY = new JLabel("Y : ");
    JSpinner jTranslasiX = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
    JSpinner jTranslasiY = new JSpinner(new SpinnerNumberModel(0, -500, 500, 1));
    JButton btnTranslasi = new JButton("Translasi");

    JLabel lScale = new JLabel("Scaling");
    JLabel lScale2 = new JLabel("Skala : ");
    JSpinner jScale = new JSpinner(new SpinnerNumberModel(1, 0.25, 2, 0.25));
    JButton btnScale = new JButton("Scaling");

    JLabel lRotasi = new JLabel("Rotasi");
    JLabel lRotasi2 = new JLabel("Sudut : ");
    JSpinner jRotasi = new JSpinner(new SpinnerNumberModel(0, 0, 360, 1));
    JButton btnRotasi = new JButton("Rotasi");

    JLabel lSkew = new JLabel("Shear/Skew");
    JLabel lSkewX = new JLabel("X : ");
    JLabel lSkewY = new JLabel("Y : ");
    JSpinner jSkewX = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
    JSpinner jSkewY = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
    JButton btnSkew = new JButton("Shear");

    String dataObjek[][] = {{"Drawline", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/pencil.png"},
            {"Square", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/square.png"},
            {"Rectangle", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/rectangle.png"},
            {"Triangle", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/segitiga.png"},
            {"Circle", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/circle.png"},
            {"Oval", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/oval.png"},
//            {"Hexagon", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/hexagon.png"},
            {"Trapezoid", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/trapezoid.png"},
            {"Line", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/line.png"}
    };

    String dataCustomLine[][] = {{"Line", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/line.png"},
            {"Dash", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/dashline.png"},
            {"Dot", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/dotline.png"},
            {"DashDot", "E:/1 KAKA/1 IF UPNYK/6 SEM/Grafkom/Projek/projek-rifka/icon/dashdotline.png"}
    };

    String dataStroke[] = {"1f", "3f", "5f", "7f"};


    public Drawing() {
        setTitle("Drawing in java (Grafika Komputer dan Multimedia)");
        setSize(1500, 900);
        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        container.add(panel, BorderLayout.NORTH);
        container.add(panel2, BorderLayout.WEST);
        container.add(panel3, BorderLayout.EAST);
        container.add(shaping, BorderLayout.CENTER);

        box = Box.createVerticalBox();
        box2 = Box.createVerticalBox();

        box4 = Box.createVerticalBox();

        box4.add(lTranslasi);
        box4.add(lTranslasiX);
        box4.add(jTranslasiX);
        box4.add(lTranslasiY);
        box4.add(jTranslasiY);

        box4.add(btnTranslasi);
        btnTranslasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesTranslasi();
//                shaping.objectType="";

            }
        });

        box4.add(lRotasi);
        box4.add(lRotasi2);
        box4.add(jRotasi);
        box4.add(btnRotasi);
        btnRotasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesRotasi();
            }
        });

        box4.add(lScale);
        box4.add(lScale2);
        box4.add(jScale);
        box4.add(btnScale);
        btnScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesScaling();
            }
        });

        box4.add(lSkew);
        box4.add(lSkewX);
        box4.add(jSkewX);
        box4.add(lSkewY);
        box4.add(jSkewY);
        box4.add(btnSkew);
        btnSkew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getSkewX().equals("0") && !getSkewY().equals("0")){
                    JOptionPane.showMessageDialog(null, "Pilih salah satu sisi (X/Y)");
                } else {
                    prosesSkew();
                }
            }
        });

        for (int i = 0; i < dataObjek.length; i++) {
            makeObjectButton(dataObjek[i][1], dataObjek[i][0]);
        }

        box.add(lTebal);
        for (int i = 0; i < dataStroke.length; i++) {
            makeStrokeLineButton(dataStroke[i]);
        }

        box.add(lStroke);
        for (int i = 0; i < dataCustomLine.length; i++) {
            makeCustomLineButton(dataCustomLine[i][1], dataCustomLine[i][0]);
        }

        JButton btnFillColor = new JButton("Change Fill Color");
        panel.add(btnFillColor);
        btnFillColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Color color = JColorChooser.showDialog(Drawing.this,
                        "Choose a color", fillColor);
                if (color != null) { // new color selected
                    fillColor = color;
                    shaping.fillColor = color;
                    shaping.lineColor = Color.black;
                }
//                panel3.setBackground(chooserColor); // change panel's background color
            }
        });

        JButton btnLineColor = new JButton("Change Line Color");
        panel.add(btnLineColor);
        btnLineColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Color color = JColorChooser.showDialog(Drawing.this,
                        "Choose a color", lineColor);
                if (color != null) { // new color selected
                    lineColor = color;
                    shaping.lineColor = color;
                    shaping.fillColor = Color.white;
                }
//                panel3.setBackground(chooserColor); // change panel's background color
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AffineTransform af = new AffineTransform();
                shaping.graphics2D.setTransform(af);
                shaping.clearAll();
            }
        });

        panel.add(clearButton);
        panel2.add(box);
        panel3.add(box4);
        panel3.add(box2);

    }

    public void makeObjectButton(String path, String object) {
        Image scaled;
        JButton objButton = new JButton();
        objButton.setPreferredSize(new Dimension(50, 50));
        try {
            scaled = ImageIO.read(new File(path)).getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            objButton.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
            e.printStackTrace();
        }
        box.add(objButton);
        objButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shaping.selected = 1;
                shaping.objectType = object;
//                System.out.println(object);
            }
        });


    }

    public void makeCustomLineButton(String path, String object) {
        Image scaled;
        JButton objButton = new JButton();
        objButton.setPreferredSize(new Dimension(50, 50));
        try {
            scaled = ImageIO.read(new File(path)).getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            objButton.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
            e.printStackTrace();
        }
        box.add(objButton);
        objButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shaping.selected = 1;
                shaping.lineType = object;
//                System.out.println(object);
            }
        });


    }

    public void makeStrokeLineButton(String object) {
        JButton objButton = new JButton(object);

        objButton.setPreferredSize(new Dimension(50, 50));
        box.add(objButton);
        objButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shaping.selected = 1;
                shaping.strokeType = object;
//                System.out.println(object);
            }
        });
    }

    public void prosesTranslasi() {
        try{
            String sX, sY;
            int x, y;
            sX = getTranslasiX();
            sY = getTranslasiY();

            x = Integer.parseInt(sX);
            y = Integer.parseInt(sY);

            AffineTransform af = new AffineTransform();
            af.translate(x, -y);
            shaping.graphics2D.transform(af);
            shaping.redrawingObjek();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void prosesScaling() {
        try {
            String sScale;
            double scale;
            sScale = getScale();
            scale = Double.parseDouble(sScale);

            AffineTransform af = new AffineTransform();
            af.translate(-(shaping.midX * (scale - 1)), -(shaping.midY * (scale - 1)));
            af.scale(scale, scale);
            shaping.graphics2D.transform(af);
            shaping.redrawingObjek();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void prosesRotasi() {
        try {
            String sRotate;
            double rotate;
            sRotate = getRotasi();
            rotate = Math.toRadians(Double.parseDouble(sRotate));
            AffineTransform af = new AffineTransform();
            af.rotate(rotate, shaping.midX, shaping.midY);
            shaping.graphics2D.transform(af);
            shaping.redrawingObjek();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void prosesSkew(){
        String sX, sY;
        double x, y;
        sX = getSkewX();
        sY = getSkewY();

        x = Double.parseDouble(sX);
        y = Double.parseDouble(sY);

        int jarakX, jarakY;
        if(x == 0){
            jarakX = 0;
//            jarakY = shaping.oldY*5;
            jarakY = (int) shaping.midY*2;
        } else {
//            jarakX = -shaping.oldX/5;
            jarakX = (int) -shaping.midX/2;
            jarakY = 0;
        }

        AffineTransform af = new AffineTransform();
        af.translate(jarakX, -jarakY);
        af.shear(x, y);
        shaping.graphics2D.transform(af);
        shaping.redrawingObjek();
    }

    public String getTranslasiX() {
        return String.valueOf(jTranslasiX.getValue());
    }

    public String getTranslasiY() {
        return String.valueOf(jTranslasiY.getValue());
    }

    public String getScale() {
        return String.valueOf(jScale.getValue());
    }

    public String getRotasi() {
        return String.valueOf(jRotasi.getValue());
    }

    public String getSkewX() {
        return String.valueOf(jSkewX.getValue());
    }

    public String getSkewY() {
        return String.valueOf(jSkewY.getValue());
    }
}

