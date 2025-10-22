package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class ModernTabbedPaneUI extends BasicTabbedPaneUI {

    private final Color selectedColor = new Color(41, 128, 185); // ACCENT_COLOR
    private final Color unselectedColor = new Color(52, 73, 94); // PRIMARY_COLOR
    private final Color backgroundColor = new Color(236, 240, 241); // SECONDARY_COLOR
    private final Color lineColor = new Color(41, 128, 185); // ACCENT_COLOR

    @Override
    protected void installDefaults() {
        super.installDefaults();
        tabAreaInsets.left = 0;
        selectedTabPadInsets = new Insets(0, 0, 0, 0);
        contentBorderInsets = new Insets(1, 0, 0, 0);
    }

    @Override
    protected Insets getTabInsets(int tabPlacement, int tabIndex) {
        return new Insets(10, 15, 10, 15); // Aumenta o padding interno da aba
    }

    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, tabPane.getWidth(), rects[0].height + tabAreaInsets.top + tabAreaInsets.bottom);
        super.paintTabArea(g, tabPlacement, selectedIndex);
    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        // Não desenha nenhuma borda padrão
    }

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isSelected) {
            g2.setColor(selectedColor);
        } else {
            g2.setColor(unselectedColor);
        }
        
        // Desenha um retângulo simples para o fundo da aba
        g2.fillRect(x, y, w, h);

        g2.dispose();
    }

    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
        // Desenha uma linha abaixo da área de abas para conectar ao conteúdo
        int tabAreaHeight = calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
        g.setColor(lineColor);
        g.fillRect(0, tabAreaHeight, tabPane.getWidth(), contentBorderInsets.top);
    }

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        // Não desenha o indicador de foco pontilhado
    }

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        g.setFont(font);
        
        if (isSelected) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(new Color(200, 200, 200)); // Cor mais suave para abas não selecionadas
        }
        
        // Centraliza o texto
        int x = textRect.x + (textRect.width - metrics.stringWidth(title)) / 2;
        int y = textRect.y + (textRect.height - metrics.getHeight()) / 2 + metrics.getAscent();
        
        g.drawString(title, x, y);
    }
}
