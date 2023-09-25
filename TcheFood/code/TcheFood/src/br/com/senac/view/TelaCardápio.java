package br.com.senac.view;

import br.com.senac.dao.ProdutoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TelaCardápio {
    public JPanel JPCardápio;
    private JLabel JLCardápioTitulo;
    private JTable table1;
    private JButton pagarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton adicionarButton;
    private JButton adicionarButton1;
    private JButton adicionarButton2;
    private JButton adicionarButton3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton adicionarButton4;
    private JButton adicionarButton5;
    private JButton adicionarButton6;
    private JButton adicionarButton7;
    private JScrollPane JScroll;
    private JButton removerButton;
    private JTextField tfNewNome;
    private JTextField tfNewDescricao;
    private JTextField tfNewPreco;
    private JButton adicionarAoCardápioButton;
    private JComboBox cbbCategoriaId;
    private JButton editarButton;
    private DefaultTableModel tableModel;
    private TelaPagamento telaPagamento;
    private Set<String> itensSelecionados = new HashSet<>();
    private double valorTotal = 0.0;
    private ArrayList<String> produtosAdicionados = new ArrayList<>();

    public void setTelaPagamento(TelaPagamento telaPagamento) {
        this.telaPagamento = telaPagamento;
    }

    public void atualizarValorNaTelaPagamento(double precoProduto) {
        telaPagamento.atualizarValor(precoProduto);
    }

    public Container JPCardapio() {
        return JPCardápio;
    }

    public static void main(String[] args) {

        TelaPagamento telaPagamento = new TelaPagamento();
        TelaCardápio telaCardapio = new TelaCardápio();

        telaCardapio.setTelaPagamento(telaPagamento);

        JFrame frame = new JFrame("TelaCardápio");
        frame.setContentPane(new TelaCardápio().JPCardápio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    public TelaCardápio() {

        pagarButton.setEnabled(false);

        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        textField4.setEditable(false);
        textField5.setEditable(false);
        textField6.setEditable(false);
        textField7.setEditable(false);
        textField8.setEditable(false);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("Preço");

        table1.setModel(tableModel);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Nome");
        tableModel.addColumn("Preço");

        // Defina o modelo de tabela na JTable
        table1.setModel(tableModel);

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTextField[] textFields = {textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8};
        for (JTextField textField : textFields) {
            textField.setEditable(false);
        }

        ProdutoDAO produtoDAO = new ProdutoDAO();


        List<Integer> idsProdutos = produtoDAO.obterIDsDosProdutosParaExibir();

        List<String> nomesProdutos = new ArrayList<>();
        int numTextFields = textFields.length;

        for (int i = 0; i < numTextFields; i++) {
            if (i < idsProdutos.size()) {
                String nomeProduto = produtoDAO.obterNomesDosProdutosDoBancoDeDados(idsProdutos.get(i));
                nomesProdutos.add(nomeProduto);
                textFields[i].setText(nomeProduto);
            }
        }

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPagamento telaPagamento = new TelaPagamento();

                JFrame frame = new JFrame("Tela de Pagamento");
                frame.setContentPane(telaPagamento.JPPagamento);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                atualizarValorNaTelaPagamento(valorTotal);
            }
        });

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho(textField1.getText(), 9.99);
            }
        });

        adicionarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho(textField2.getText(), 10.99);
            }
        });
        adicionarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho(textField3.getText(), 12.99);
            }
        });
        adicionarButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho(textField4.getText(), 10.99);
            }
        });
        adicionarButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho(textField5.getText(), 5.99);
            }
        });
        adicionarButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho(textField6.getText(), 4.99);
            }
        });
        adicionarButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho(textField7.getText(), 4.99);
            }
        });
        adicionarButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho(textField8.getText(), 6.99);
            }
        });
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerUltimoItem();
            }
        });
        adicionarAoCardápioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarNovoProduto();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProduto();
            }
        });
    }

    private void adicionarProdutoAoCarrinho(String nomeProduto, double precoProduto) {
        Vector<String> row = new Vector<>();
        row.add(nomeProduto);
        row.add(String.valueOf(precoProduto));
        tableModel.addRow(row);

        itensSelecionados.add(nomeProduto);
        valorTotal += precoProduto;

        if (telaPagamento != null) {
            telaPagamento.atualizarValor(precoProduto);
        }

        pagarButton.setEnabled(true);
    }

    private void removerUltimoItem() {
        int rowCount = tableModel.getRowCount();

        if (rowCount <= 1) {
            pagarButton.setEnabled(false);
        }

        if (rowCount > 0) {
            tableModel.removeRow(rowCount - 1);

            if (!produtosAdicionados.isEmpty()) {
                String nomeProdutoRemovido = produtosAdicionados.remove(produtosAdicionados.size() - 1);

                itensSelecionados.remove(nomeProdutoRemovido);
                valorTotal -= obterPrecoDoProduto(nomeProdutoRemovido);

                if (telaPagamento != null) {
                    telaPagamento.atualizarValor(-obterPrecoDoProduto(nomeProdutoRemovido));
                }
            }
        }
    }
    private double obterPrecoDoProduto(String nomeProduto) {
        if ("Produto 1".equals(nomeProduto)) {
            return 9.99;
        } else if ("Produto 2".equals(nomeProduto)) {
            return 10.99;
        }
        return 0.0;
    }
    private void adicionarNovoProduto() {
        String nome = tfNewNome.getText();
        String descricao = tfNewDescricao.getText();
        double preco = Double.parseDouble(tfNewPreco.getText());

        ProdutoDAO produtoDAO = new ProdutoDAO();

        String categoriaSelecionada = (String) cbbCategoriaId.getSelectedItem();
        int categoriaId;

        if ("Hambúrgueres".equals(categoriaSelecionada)) {
            categoriaId = 1;
        } else if ("Acompanhamentos".equals(categoriaSelecionada)) {
            categoriaId = 2;
        } else if ("Bebidas".equals(categoriaSelecionada)) {
            categoriaId = 3;
        } else {
            JOptionPane.showMessageDialog(this.JPCardapio(), "Selecione uma categoria válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int novoID = produtoDAO.adicionarProduto(nome, descricao, preco, categoriaId);

            Vector<String> rowData = new Vector<>();
            rowData.add(nome);
            rowData.add(String.valueOf(preco));
            tableModel.addRow(rowData);
            tfNewNome.setText("");
            tfNewDescricao.setText("");
            tfNewPreco.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.JPCardapio(), "Categoria inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void editarProduto() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(JPCardápio, "Selecione um produto para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nomeProdutoSelecionado = (String) table1.getValueAt(selectedRow, 0);

        String novoNome = JOptionPane.showInputDialog(JPCardápio, "Novo nome para o produto:", nomeProdutoSelecionado);
        if (novoNome == null || novoNome.isEmpty()) {
            JOptionPane.showMessageDialog(JPCardápio, "Nome de produto inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novaDescricao = JOptionPane.showInputDialog(JPCardápio, "Nova descrição para o produto:");
        if (novaDescricao == null) {
            novaDescricao = "";
        }

        String novoPrecoStr = JOptionPane.showInputDialog(JPCardápio, "Novo preço para o produto:");
        if (novoPrecoStr == null || novoPrecoStr.isEmpty()) {
            JOptionPane.showMessageDialog(JPCardápio, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double novoPreco = Double.parseDouble(novoPrecoStr);

            tableModel.setValueAt(novoNome, selectedRow, 0);
            tableModel.setValueAt(String.valueOf(novoPreco), selectedRow, 1);

            JOptionPane.showMessageDialog(JPCardápio, "Produto atualizado com sucesso.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(JPCardápio, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}