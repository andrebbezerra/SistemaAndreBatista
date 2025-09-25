
package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Demonstração do menu modernizado - gera imagem do design
 */
public class MenuDemo {
    
    // Cores do tema moderno
    private static final Color PRIMARY_COLOR = new Color(52, 73, 94);      // Azul escuro
    private static final Color SECONDARY_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color ACCENT_COLOR = new Color(41, 128, 185);     // Azul médio
    private static final Color TEXT_COLOR = new Color(44, 62, 80);         // Cinza escuro
    private static final Color HOVER_COLOR = new Color(52, 152, 219);      // Azul claro
    private static final Color WHITE = Color.WHITE;
    
    public static void main(String[] args) {
        try {
            // Cria uma imagem do menu modernizado
            int width = 1000;
            int height = 700;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            
            // Ativa antialiasing para melhor qualidade
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            // Desenha o fundo principal
            g2d.setColor(SECONDARY_COLOR);
            g2d.fillRect(0, 0, width, height);
            
            // Desenha a barra de menu
            g2d.setColor(PRIMARY_COLOR);
            g2d.fillRect(0, 0, width, 60);
            
            // Desenha os itens do menu
            g2d.setColor(WHITE);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 14));
            FontMetrics fm = g2d.getFontMetrics();
            
            String[] menuItems = {"📋 Cadastros", "🔍 Consultas", "📊 Relatórios"};
            int x = 20;
            for (String item : menuItems) {
                g2d.drawString(item, x, 35);
                x += fm.stringWidth(item) + 40;
            }
            
            // Botão de configurações no canto direito
            String configText = "⚙️ Config";
            int configX = width - fm.stringWidth(configText) - 20;
            g2d.setColor(ACCENT_COLOR);
            g2d.fillRoundRect(configX - 10, 15, fm.stringWidth(configText) + 20, 30, 5, 5);
            g2d.setColor(WHITE);
            g2d.drawString(configText, configX, 35);
            
            // Área de conteúdo principal
            g2d.setColor(WHITE);
            g2d.fillRect(30, 90, width - 60, height - 170);
            
            // Título principal
            g2d.setColor(PRIMARY_COLOR);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 32));
            fm = g2d.getFontMetrics();
            String title = "Sistema Integrado Gerencial";
            int titleX = (width - fm.stringWidth(title)) / 2;
            g2d.drawString(title, titleX, 150);
            
            // Subtítulo
            g2d.setColor(TEXT_COLOR);
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            fm = g2d.getFontMetrics();
            String subtitle = "Bem-vindo ao seu sistema de gestão";
            int subtitleX = (width - fm.stringWidth(subtitle)) / 2;
            g2d.drawString(subtitle, subtitleX, 180);
            
            // Cards de acesso rápido
            int cardWidth = 250;
            int cardHeight = 200;
            int cardSpacing = 30;
            int totalCardsWidth = 3 * cardWidth + 2 * cardSpacing;
            int startX = (width - totalCardsWidth) / 2;
            int cardY = 220;
            
            String[] cardTitles = {"📋 Cadastros", "🔍 Consultas", "📊 Relatórios"};
            String[] cardDescs = {
                "Gerencie clientes, produtos e fornecedores",
                "Consulte vendas, estoque e relatórios",
                "Gere relatórios detalhados do sistema"
            };
            
            for (int i = 0; i < 3; i++) {
                int cardX = startX + i * (cardWidth + cardSpacing);
                
                // Desenha o card
                g2d.setColor(WHITE);
                g2d.fillRoundRect(cardX, cardY, cardWidth, cardHeight, 10, 10);
                g2d.setColor(new Color(220, 220, 220));
                g2d.drawRoundRect(cardX, cardY, cardWidth, cardHeight, 10, 10);
                
                // Ícone do card
                g2d.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
                fm = g2d.getFontMetrics();
                String icon = cardTitles[i].substring(0, 2);
                int iconX = cardX + (cardWidth - fm.stringWidth(icon)) / 2;
                g2d.drawString(icon, iconX, cardY + 70);
                
                // Título do card
                g2d.setColor(PRIMARY_COLOR);
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
                fm = g2d.getFontMetrics();
                String cardTitle = cardTitles[i].substring(3);
                int titleCardX = cardX + (cardWidth - fm.stringWidth(cardTitle)) / 2;
                g2d.drawString(cardTitle, titleCardX, cardY + 110);
                
                // Descrição do card
                g2d.setColor(TEXT_COLOR);
                g2d.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                fm = g2d.getFontMetrics();
                
                // Quebra a descrição em linhas
                String[] words = cardDescs[i].split(" ");
                StringBuilder line = new StringBuilder();
                int lineY = cardY + 135;
                
                for (String word : words) {
                    if (fm.stringWidth(line + word + " ") < cardWidth - 20) {
                        line.append(word).append(" ");
                    } else {
                        String lineText = line.toString().trim();
                        int lineX = cardX + (cardWidth - fm.stringWidth(lineText)) / 2;
                        g2d.drawString(lineText, lineX, lineY);
                        line = new StringBuilder(word + " ");
                        lineY += 15;
                    }
                }
                if (line.length() > 0) {
                    String lineText = line.toString().trim();
                    int lineX = cardX + (cardWidth - fm.stringWidth(lineText)) / 2;
                    g2d.drawString(lineText, lineX, lineY);
                }
            }
            
            // Barra de status
            g2d.setColor(PRIMARY_COLOR);
            g2d.fillRect(0, height - 40, width, 40);
            
            g2d.setColor(WHITE);
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2d.drawString("Sistema para pontos de venda e emissão de notas fiscais.", 20, height - 20);
            g2d.drawString("👤 Usuário: admin", width - 150, height - 20);
            
            g2d.dispose();
            
            // Salva a imagem
            ImageIO.write(image, "PNG", new File("/home/ubuntu/menu_modernizado_preview.png"));
            System.out.println("Imagem do menu modernizado gerada com sucesso: menu_modernizado_preview.png");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

