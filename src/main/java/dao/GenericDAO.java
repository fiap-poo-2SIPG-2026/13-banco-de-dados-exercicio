package dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    public void inserir(T entidade);
    public abstract List<T> listar();
    public abstract void atualizar(T entidade);
    public abstract void excluir(ID id);
}
