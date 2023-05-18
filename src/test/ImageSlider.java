package test;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import scrollbar.ScrollBar;
import swing.ImageItem;
import swing.ImageItem;

public class ImageSlider extends javax.swing.JPanel {

    private final MigLayout imageLayout;

    public ImageSlider() {
        initComponents();
        imageLayout = new MigLayout("al center, filly", "10[]10");
        panelItem.setLayout(imageLayout);
        ScrollBar sb = new ScrollBar();
        sb.setOrientation(ScrollBar.HORIZONTAL);
        sp.setHorizontalScrollBar(sb);
        testImage();
        panelItem.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
    }

    private void testImage() {
        panelItem.add(getItem(new ImageIcon(getClass().getResource("/icon/image1.png"))), "w 300, h 300");
        panelItem.add(getItem(new ImageIcon(getClass().getResource("/icon/image2.png"))), "w 300, h 300");
        panelItem.add(getItem(new ImageIcon(getClass().getResource("/icon/image3.png"))), "w 300, h 300");
        panelItem.add(getItem(new ImageIcon(getClass().getResource("/icon/image4.png"))), "w 300, h 300");
        panelItem.add(getItem(new ImageIcon(getClass().getResource("/icon/image5.png"))), "w 300, h 300");
//        panelItem.add(getItem(new ImageIcon(getClass().getResource("/image/img-6.jpg"))), "w 250, h 200");
//        panelItem.add(getItem(new ImageIcon(getClass().getResource("/image/img-7.jpg"))), "w 250, h 200");

    }

    private ImageItem getItem(Icon icon) {
        return new ImageItem(icon, imageLayout);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panelItem = new javax.swing.JPanel();

        setBackground(new Color(0.0f, 0.0f, 0.0f, 0.3f));

        sp.setBorder(null);
        sp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panelItem.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        javax.swing.GroupLayout panelItemLayout = new javax.swing.GroupLayout(panelItem);
        panelItem.setLayout(panelItemLayout);
        panelItemLayout.setHorizontalGroup(
            panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1046, Short.MAX_VALUE)
        );
        panelItemLayout.setVerticalGroup(
            panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        sp.setViewportView(panelItem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setBackground(Color color) {
        super.setBackground(color);
        if (panelItem != null) {
            panelItem.setBackground(color);
            sp.getHorizontalScrollBar().setBackground(color);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelItem;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
