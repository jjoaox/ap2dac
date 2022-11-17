package bean;

import static util.MessageUtil.addErrorMessage;
import static util.MessageUtil.addInfoMessage;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.JogoDao;
import entidade.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> jogos;
	private Integer mValor;

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public String salvar() {
		try {
			jogo.setDataCadastro(new Date());
			JogoDao.salvar(jogo);
			addInfoMessage("Sucesso", "Jogo salvo com sucesso.");
			jogo = new Jogo();
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao salvar o jogo.");
		}
		
		return null;
	}
	
	public String deletar(Jogo jogo) {
		try {
			JogoDao.deletar(jogo);
			addInfoMessage("Sucesso", "Jogo deletado com sucesso."); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addErrorMessage("Erro", "Erro ao deletar jogo.");
		}
		return null;	
	}
	
	public String atualizar() {
		
		try {
			Jogo j = JogoDao.buscarPorId(jogo.getId());
			jogo.setDataCadastro(j.getDataCadastro());
			JogoDao.atualizar(jogo);
			addInfoMessage("Sucesso", "Valores atualizados com sucesso.");
			jogos = JogoDao.listarJogos(); 
			return "listagem";
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao atualizar valores.");
		}
		return null;
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public List<Jogo> getJogos() {
		if (jogos == null) {
			jogos = JogoDao.listarJogos();
		}
		return jogos;
	}
	public void setJogadas(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	public Integer maiorValor(){
		Jogo j = JogoDao.buscarPorId(jogo.getId());
		if(j.getV1() > j.getV2() && j.getV1() > j.getV3() && j.getV1() > j.getV4() && j.getV1() > j.getV5()){
			return mValor = j.getV1();
		}else if(j.getV2() > j.getV1() && j.getV2() > j.getV3() && j.getV2() > j.getV4() && j.getV2() > j.getV5()){
			return mValor = j.getV2();
		}else if(j.getV3() > j.getV1() && j.getV3() > j.getV2() && j.getV3() > j.getV4() && j.getV3() > j.getV5()){
			return mValor = j.getV3();
		}else if(j.getV4() > j.getV1() && j.getV4() > j.getV2() && j.getV4() > j.getV3() && j.getV4() > j.getV5()){
			return mValor = j.getV4();
		}else if(j.getV5() > j.getV1() && j.getV5() > j.getV2() && j.getV5() > j.getV3() && j.getV5() > j.getV4()){
			return mValor = j.getV5();
		}
		return mValor;
	}
	
	public Integer getmValor() {
		return mValor;
	}

	public void setmValor(Integer mValor) {
		this.mValor = mValor;
	}
}
