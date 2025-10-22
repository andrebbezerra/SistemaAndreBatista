package view;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Empresa;
import control.ControleDb;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import control.ConsultaCep;
import model.Endereco;

/**
 * Formulário de Cadastro de Empresa como uma janela interna (JInternalFrame),
 * projetado para ser usado dentro de um JDesktopPane.
 */
public class CadastroEmpresaFrame extends JInternalFrame {
    
    // Variáveis de instância para os atributos da empresa
    private int Codigo;
    private String RazaoSocial;
    private String NomeResumido;
    private String Cnpj;
    private int InscEstadual;
    private String Telefone;
    private String WhatSapp;
    private String Logradouro;
    private String Numero;
    private String Bairro;
    private String Cidade;
    private String Estado;
    private int Cep;
    private String Ibge;
    private String InscMunicipal;
    private String Cnae;
    private String Responsavel;
    private int CpfRespon;
    private String RegimeTrib;
    private String Logomarca;

    // Variáveis de instância para os componentes de UI
    // Aba 'Dados Principais'
    private JTextField codigoField;
    private JTextField razaoSocialField;
    private JTextField nomeFantasiaField;
    private JTextField cnpjField;
    private JTextField inscEstadualField;
    private JTextField inscMunicipalField;
    private JComboBox<String> regimeTributarioComboBox;
    
    // Aba 'Endereço e Contato'
    private JTextField cepField;
    private JTextField logradouroField;
    private JTextField numeroField;
    private JTextField bairroField;
    private JTextField cidadeField;
    private JTextField ufField;
    private JTextField telefoneField;
    private JCheckBox whatsappCheckBox;
    
    // Aba 'Responsável e Logomarca'
    private JTextField responsavelField;
    private JTextField cpfResponsavelField;
    private JTextField logoPathField;
    private JLabel logoPreviewLabel;

    public CadastroEmpresaFrame() {
        // 1. Configuração da Janela Interna (JInternalFrame)
        super("Cadastro de Empresa", // Título
              true,                  // Resizable (redimensionável)
              true,                  // Closable (fechável)
              true,                  // Maximizable (maximizável)
              true);                 // Iconifiable (minimizável)

        // Define o tamanho inicial da janela interna
        setSize(700, 500);
        
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        
        // Opcionalmente, defina uma borda vazia para evitar problemas de layout
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

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
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDados();
            }
        });

        buttonPanel.add(salvarButton);

        // Adiciona o painel de botões na parte inferior
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal ao content pane do JInternalFrame
        add(mainPanel);
        
        // Adiciona um WindowListener para focar quando a janela for aberta
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                razaoSocialField.requestFocus(); // Pede o foco ao abrir a janela
            }
        });
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
        gbc.gridx = 1; codigoField = new JTextField("1", 5); codigoField.setEnabled(false); panel.add(codigoField, gbc);
        gbc.gridx = 2; panel.add(new JLabel("Razão Social:"), gbc);
        gbc.gridx = 3; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; razaoSocialField = new JTextField();panel.add(razaoSocialField, gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // --- Linha 1: Nome Fantasia ---
        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Nome Fantasia:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 5; gbc.fill = GridBagConstraints.HORIZONTAL; nomeFantasiaField = new JTextField(); panel.add(nomeFantasiaField, gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

        // --- Linha 2: CNPJ e Inscrição Estadual ---
        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("CNPJ:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; cnpjField = new JTextField(); panel.add(cnpjField, gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3; panel.add(new JLabel("Insc. Estadual:"), gbc);
        gbc.gridx = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; inscEstadualField = new JTextField(); panel.add(inscEstadualField, gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

        // --- Linha 3: Inscrição Municipal e Código Regime Tributário ---
        gbc.gridy = 3; gbc.gridx = 0; panel.add(new JLabel("Insc. Municipal:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; inscMunicipalField = new JTextField(); panel.add(inscMunicipalField, gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3; panel.add(new JLabel("Cód. Regime Tributário:"), gbc);
        gbc.gridx = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; String[] regimes = {"Simples Nacional", "Lucro Presumido", "Lucro Real"}; regimeTributarioComboBox = new JComboBox<>(regimes); panel.add(regimeTributarioComboBox, gbc);
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
        gbc.gridx = 1; cepField = new JTextField(10); panel.add(cepField, gbc);
        gbc.gridx = 2; panel.add(new JLabel("Logradouro:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; logradouroField = new JTextField(); panel.add(logradouroField, gbc);
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        
        cepField.addFocusListener(new FocusListener() {
        @Override
        public void focusLost(FocusEvent e) {
            
            if(!cepField.getText().isEmpty()){
                Endereco endereco = ConsultaCep.buscarEndereco(cepField.getText());
            
            logradouroField.setText(endereco.getLogradouro()); 
            bairroField.setText(endereco.getBairro());
            cidadeField.setText(endereco.getLocalidade());
            ufField.setText(endereco.getUf());
            
            }
         
        }

        @Override
        public void focusGained(FocusEvent e) {}
        });
 

        // --- Linha 1: Número e Bairro ---
        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1; numeroField = new JTextField(5); panel.add(numeroField, gbc);
        gbc.gridx = 2; panel.add(new JLabel("Bairro:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL; bairroField = new JTextField(); panel.add(bairroField, gbc);
        gbc.fill = GridBagConstraints.NONE;

        // --- Linha 2: Cidade e Estado ---
        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("Cidade:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; cidadeField = new JTextField(); panel.add(cidadeField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2; panel.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL; ufField = new JTextField(); panel.add(ufField, gbc);

        // --- Linha 3: Telefone e WhatsApp ---
        gbc.gridy = 3; gbc.gridx = 0; panel.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; telefoneField = new JTextField(12); panel.add(telefoneField, gbc);
        gbc.gridx = 2; whatsappCheckBox = new JCheckBox("É WhatsApp?"); panel.add(whatsappCheckBox, gbc);

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
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; responsavelField = new JTextField(); panel.add(responsavelField, gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // --- Linha 1: CPF do Responsável ---
        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("CPF do Responsável:"), gbc);
        gbc.gridx = 1; cpfResponsavelField = new JTextField(15); panel.add(cpfResponsavelField, gbc);

        // --- Linha 2: Logomarca ---
        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("Logomarca:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; logoPathField = new JTextField(); logoPathField.setEditable(false); panel.add(logoPathField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3; gbc.gridwidth = 1; JButton selecionarArquivoButton = new JButton("Selecionar Arquivo..."); panel.add(selecionarArquivoButton, gbc);

        // --- Linha 3: Preview da Logomarca ---
        gbc.gridy = 3; gbc.gridx = 1; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER; logoPreviewLabel = new JLabel("Preview da Imagem", SwingConstants.CENTER); logoPreviewLabel.setPreferredSize(new Dimension(150, 150)); logoPreviewLabel.setBorder(BorderFactory.createEtchedBorder()); panel.add(logoPreviewLabel, gbc);

        // Ação do botão para selecionar arquivo
        selecionarArquivoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(panel);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                logoPathField.setText(selectedFile.getAbsolutePath());
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                logoPreviewLabel.setIcon(imageIcon);
                logoPreviewLabel.setText(null);
            }
        });

        // Painel "fantasma" para empurrar o conteúdo para cima
        gbc.gridy = 4; gbc.weighty = 1.0; panel.add(new JLabel(), gbc);
        return panel;
    }
    
    /**
     * Captura todos os dados do formulário e retorna uma instância de Empresa preenchida.
     * @return Empresa com os dados preenchidos do formulário
     */
    public Empresa capturarDados() {
        Empresa empresa = new Empresa(Codigo, RazaoSocial, NomeResumido, Cnpj, Estado, Telefone, WhatSapp, Logradouro, Numero, Bairro, Cidade, Estado, Cep, Ibge, InscMunicipal, Cnae, Responsavel, CpfRespon, RegimeTrib, Logomarca);
        
        try {
            // Dados Principais
            empresa.setCodigo(Integer.parseInt(codigoField.getText().trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao converter dados numéricos. Verifique o campo: Código.", 
                "Erro de Conversão", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
         try {
            empresa.setRazaoSocial(razaoSocialField.getText().trim());
            empresa.setNomeResumido(nomeFantasiaField.getText().trim());
            empresa.setCnpj(cnpjField.getText().trim());
            
            // Verifica se o campo inscrição estadual não está vazio antes de converter
            String inscEstadualText = inscEstadualField.getText().trim();
            if (!inscEstadualText.isEmpty()) {
                empresa.setInscEstadual(inscEstadualField.getText().trim());
            }
            
            empresa.setInscMunicipal(inscMunicipalField.getText().trim());
            empresa.setRegimeTrib((String) regimeTributarioComboBox.getSelectedItem());
            
            // Endereço e Contato
            String cepText = cepField.getText().trim();
            if (!cepText.isEmpty()) {
                empresa.setCep(Integer.parseInt(cepText));
            }
            
            empresa.setLogradouro(logradouroField.getText().trim());
            empresa.setNumero(numeroField.getText().trim());
            empresa.setBairro(bairroField.getText().trim());
            empresa.setCidade(cidadeField.getText().trim());
            empresa.setEstado(ufField.getText().trim());
            empresa.setTelefone(telefoneField.getText().trim());
            
            // Define WhatsApp baseado no checkbox
            if (whatsappCheckBox.isSelected()) {
                empresa.setWhatSapp(telefoneField.getText().trim());
            } else {
                empresa.setWhatSapp("");
            }
            
            // Responsável e Logomarca
            empresa.setResponsavel(responsavelField.getText().trim());
            
            String cpfResponsavelText = cpfResponsavelField.getText().trim();
            if (!cpfResponsavelText.isEmpty()) {
                empresa.setCpfRespon(cpfResponsavelField.getText().trim());
            }
            
            empresa.setLogomarca(logoPathField.getText().trim());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao converter dados numéricos. Verifique os campos: Código, Inscrição Estadual, CEP e CPF do Responsável.", 
                "Erro de Conversão", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        return empresa;
    }
    
    private void salvarDados() {
        Empresa empresa = capturarDados();
        
        if (empresa != null) {
            
        ControleDb conexao = new ControleDb(); 
        conexao.conectar();
        
        String insertEmpresa =  "INSERT INTO public.empresa(\n" +
                        "codigo, nome, logradouro, bairro, cidade, cep, estado, cgc, inscr, nome_res, cod_ativ)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        
        try{
            PreparedStatement pst = conexao.conexao.prepareStatement(insertEmpresa);
        
            pst.setInt(1, empresa.getCodigo());
            pst.setString(2, empresa.getRazaoSocial());
            pst.setString(3, empresa.getLogradouro());
            pst.setString(4, empresa.getBairro());
            pst.setString(5, empresa.getCidade());
            pst.setInt(6, empresa.getCep());
            pst.setString(7, empresa.getEstado());
            pst.setString(8, empresa.getCnpj());
            pst.setString(12, empresa.getInscEstadual());
            pst.setString(10, empresa.getNomeResumido());
            pst.setString(11, empresa.getCnae());
        
            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0) {
                String insert = "INSERT INTO public.filial(\n" +
                        "empresa, codigo, razao_social, nome_fantasia, logradouro, bairro, cidade, uf, cep, numero_end, compl_end, inscr_estadual, responsavel, cpf_responsavel, ddd, telefone, "
                        + "whatsapp, inscr_municipal, ativa, ativ_econom, cod_pais, regime_st, cod_reg_trib, email,cgc)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";    
                try{
                    PreparedStatement pst1 = conexao.conexao.prepareStatement(insert);
        
                    pst1.setInt(1, 1);
                    pst1.setInt(2, empresa.getCodigo());
                    pst1.setString(3, empresa.getRazaoSocial());
                    pst1.setString(4, empresa.getNomeResumido());
                    pst1.setString(5, empresa.getLogradouro());
                    pst1.setString(6, empresa.getBairro());
                    pst1.setString(7, empresa.getCidade());
                    pst1.setString(8, empresa.getEstado());
                    pst1.setInt(9, empresa.getCep());
                    pst1.setString(10, empresa.getNumero());
                    pst1.setString(11, "Complemento");
                    pst1.setString(12, empresa.getInscEstadual());
                    pst1.setString(13, empresa.getResponsavel());
                    pst1.setString(14, empresa.getCpfRespon());
                    pst1.setInt(15, 81);
                    pst1.setString(16, empresa.getTelefone());
                    pst1.setString(17, "S");
                    pst1.setString(18, empresa.getInscMunicipal());
                    pst1.setString(19, "S");
                    pst1.setString(20, empresa.getCnae());
                    pst1.setInt(21, 18);
                    pst1.setString(22, "1");
                    pst1.setString(23, "1");
                    pst1.setString(24, "emailteste@gmail.com");
                    pst1.setString(25, empresa.getCnpj());
        
                    int linhasAfetadas1 = pst1.executeUpdate();

                    if (linhasAfetadas1 > 0) {
                        System.out.println("Dados inseridos com sucesso!");
                        conexao.desconectar();
                    } else {
                        System.out.println("Falha ao inserir os dados.");
                        conexao.desconectar();
                    }
        
                } catch (SQLException ex){
                    conexao.desconectar();
                    JOptionPane.showMessageDialog(this, "Problemas ao Cadastrar a Empresa" + ex.getMessage());
                }
            } else {
                System.out.println("Falha ao inserir os dados.");
                conexao.desconectar();
            }
        
            } catch (SQLException ex){
                conexao.desconectar();
                JOptionPane.showMessageDialog(this, "Problemas ao Cadastrar a Filial" + ex.getMessage());
            }
        
        }
    }
   
}