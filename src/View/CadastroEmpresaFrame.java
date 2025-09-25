package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

/**
 * Formulário de Cadastro de Empresa como uma janela interna (JInternalFrame),
 * projetado para ser usado dentro de um JDesktopPane.
 */
public class CadastroEmpresaFrame extends JInternalFrame {

    public CadastroEmpresaFrame() {
        // 1. Configuração da Janela Interna (JInternalFrame)
        super("Cadastro de Empresa", // Título
              true,                  // Resizable (redimensionável)
              true,                  // Closable (fechável)
              true,                  // Maximizable (maximizável)
              true);                 // Iconifiable (minimizável)

        // Define o tamanho inicial da janela interna
        setSize(700, 500);

        // --- O restante da construção da UI é idêntico ---

        // Painel principal com BorderLayout para organizar abas e botões
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margem interna

        // Criação do Painel com Abas (JTabbedPane)
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adiciona as abas ao painel
        tabbedPane.addTab("Dados Principais", createDadosPrincipaisPanel());
        tabbedPane.addTab("Endereço e Contato", createEnderecoContatoPanel());
        tabbedPane.addTab("Responsável e Logomarca", createResponsavelLogoPanel());

        // Adiciona o JTabbedPane ao centro do painel principal
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Criação do Painel de Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton salvarButton = new JButton("Salvar");
        JButton cancelarButton = new JButton("Cancelar");

        // Ação para o botão "Cancelar": fecha apenas esta janela interna
        cancelarButton.addActionListener(e -> dispose());

        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        // Adiciona o painel de botões na parte inferior
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal ao content pane do JInternalFrame
        add(mainPanel);
    }

    /**
     * Cria o painel para a aba "Dados Principais".
     * Usa GridBagLayout para um alinhamento preciso.
     */
    private JPanel createDadosPrincipaisPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // --- Linha 0: Código e Razão Social ---
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1; JTextField codigoField = new JTextField("1", 5); codigoField.setEnabled(false); panel.add(codigoField, gbc);
        gbc.gridx = 2; panel.add(new JLabel("Razão Social:"), gbc);
        gbc.gridx = 3; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; panel.add(new JTextField(), gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // --- Linha 1: Nome Fantasia ---
        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Nome Fantasia:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 5; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(new JTextField(), gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

        // --- Linha 2: CNPJ e Inscrição Estadual ---
        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("CNPJ:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(new JTextField(), gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3; panel.add(new JLabel("Insc. Estadual:"), gbc);
        gbc.gridx = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(new JTextField(), gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

        // --- Linha 3: Inscrição Municipal e Código Regime Tributário ---
        gbc.gridy = 3; gbc.gridx = 0; panel.add(new JLabel("Insc. Municipal:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(new JTextField(), gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3; panel.add(new JLabel("Cód. Regime Tributário:"), gbc);
        gbc.gridx = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; String[] regimes = {"Simples Nacional", "Lucro Presumido", "Lucro Real"}; panel.add(new JComboBox<>(regimes), gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

        // Painel "fantasma" para empurrar o conteúdo para cima
        gbc.gridy = 4; gbc.weighty = 1.0; panel.add(new JLabel(), gbc);
        return panel;
    }

    /**
     * Cria o painel para a aba "Endereço e Contato".
     */
    private JPanel createEnderecoContatoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // --- Linha 0: CEP e Logradouro ---
        gbc.gridy = 0; gbc.gridx = 0; panel.add(new JLabel("CEP:"), gbc);
        gbc.gridx = 1; panel.add(new JTextField(10), gbc);
        gbc.gridx = 2; panel.add(new JLabel("Logradouro:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; panel.add(new JTextField(), gbc);
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // --- Linha 1: Número e Bairro ---
        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1; panel.add(new JTextField(5), gbc);
        gbc.gridx = 2; panel.add(new JLabel("Bairro:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(new JTextField(), gbc);
        gbc.fill = GridBagConstraints.NONE;

        // --- Linha 2: Cidade e Estado ---
        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("Cidade:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(new JTextField(), gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2; panel.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 3; String[] estados = {"Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "..."}; panel.add(new JComboBox<>(estados), gbc);

        // --- Linha 3: Telefone e WhatsApp ---
        gbc.gridy = 3; gbc.gridx = 0; panel.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; panel.add(new JTextField(12), gbc);
        gbc.gridx = 2; panel.add(new JCheckBox("É WhatsApp?"), gbc);

        // Painel "fantasma" para empurrar o conteúdo para cima
        gbc.gridy = 4; gbc.weighty = 1.0; panel.add(new JLabel(), gbc);
        return panel;
    }

    /**
     * Cria o painel para a aba "Responsável e Logomarca".
     */
    private JPanel createResponsavelLogoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // --- Linha 0: Nome do Responsável ---
        gbc.gridy = 0; gbc.gridx = 0; panel.add(new JLabel("Nome do Responsável:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; panel.add(new JTextField(), gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // --- Linha 1: CPF do Responsável ---
        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("CPF do Responsável:"), gbc);
        gbc.gridx = 1; panel.add(new JTextField(15), gbc);

        // --- Linha 2: Logomarca ---
        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("Logomarca:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; JTextField logoPathField = new JTextField(); logoPathField.setEditable(false); panel.add(logoPathField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3; gbc.gridwidth = 1; JButton selecionarArquivoButton = new JButton("Selecionar Arquivo..."); panel.add(selecionarArquivoButton, gbc);

        // --- Linha 3: Preview da Logomarca ---
        gbc.gridy = 3; gbc.gridx = 1; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER; JLabel logoPreview = new JLabel("Preview da Imagem", SwingConstants.CENTER); logoPreview.setPreferredSize(new Dimension(150, 150)); logoPreview.setBorder(BorderFactory.createEtchedBorder()); panel.add(logoPreview, gbc);

        // Ação do botão para selecionar arquivo
        selecionarArquivoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(panel);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                logoPathField.setText(selectedFile.getAbsolutePath());
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                logoPreview.setIcon(imageIcon);
                logoPreview.setText(null);
            }
        });

        // Painel "fantasma" para empurrar o conteúdo para cima
        gbc.gridy = 4; gbc.weighty = 1.0; panel.add(new JLabel(), gbc);
        return panel;
    }
}
