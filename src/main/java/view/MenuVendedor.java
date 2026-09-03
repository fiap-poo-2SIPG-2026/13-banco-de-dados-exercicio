package view;

import dao.VendedorDAO;
import model.Vendedor;

import java.util.List;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;

public class MenuVendedor {
    public void menu() {
        String[] item = {"Inserir", "Pesquisar", "Listar",
                         "Atualizar", "Excluir", "Sair"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opção",
                    "*** MENU VENDEDOR ***",
                    INFORMATION_MESSAGE,
                    null,
                    item,
                    item[0]);

            switch(opcao.toLowerCase()) {
                case "inserir" -> inserir();
                case "listar" -> listar();
                case "excluir" -> excluir();
                case "atualizar" -> atualizar();
            }
        }
        while(!opcao.toLowerCase().equals("sair"));

    }

    private void atualizar() {
        int id;
        String nome;

        // pesquisar o vendedor por ID
        id = parseInt(showInputDialog("ID do vendedor"));
        Vendedor vendedor = new VendedorDAO().pesquisar(id);
        if(vendedor == null) {
            showMessageDialog(null, "Vendedor não encontrado");
        }
        else {
            nome = showInputDialog("Nome atualizado");
            vendedor.setNome(nome);
            new VendedorDAO().atualizar(vendedor);
        }

    }

    private void excluir() {
        List<Vendedor> lista = new VendedorDAO().listar();
        String aux = "";
        int id;

        for(Vendedor v : lista) {
            aux += v.getId() + "    |   "  + v.getNome() + "\n";
        }

        id = parseInt(showInputDialog("Escolha o ID para excluir " +
                "o vendedor\n\n" + aux));
        new VendedorDAO().excluir(id);

    }

    private void listar() {
        List<Vendedor> lista = new VendedorDAO().listar();
        String aux = "";
        for(Vendedor v : lista) {
            aux += v.getId() + "    |   "  + v.getNome() + "\n";
        }
        showMessageDialog(null, aux);
    }

    private void inserir() {
        Vendedor vendedor = new Vendedor();
        String nome = showInputDialog("Nome do Vendedor");
        vendedor.setNome(nome);
        new VendedorDAO().inserir(vendedor);
    }
}
