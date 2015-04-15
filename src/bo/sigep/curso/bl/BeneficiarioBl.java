package bo.sigep.curso.bl;

import java.util.List;

import bo.sigep.curso.dao.BeneficiarioDao;
import bo.sigep.curso.vo.BeneficiariosVo;

public class BeneficiarioBl {
	private BeneficiarioDao beneficiarioDao;

	public BeneficiarioBl() {
		beneficiarioDao = new BeneficiarioDao();
	}

	public int getCantidadBeneficiarioPorApellidoPaterno(String apellido) {
		int resultado = 0;
		if(apellido != null && !"".equals(apellido.trim())){
			apellido.replace("%", "_");
			apellido.replace("_", "_");
			resultado = beneficiarioDao.getCantidadBeneficiarioPorApellidoPaterno(apellido+"%"); 
		}
		return resultado;
	}

	public List<BeneficiariosVo> getBeneficiarioPorApellidoPaterno(
			String apellido, int nroPagina,int nroRegistros) {
		List<BeneficiariosVo> resultado = null;
		if(apellido != null && !"".equals(apellido.trim())){
			apellido.replace("%", "_");
			apellido.replace("_", "_");
			resultado = beneficiarioDao.getBeneficiarioPorApellidoPaterno(apellido+"%",nroPagina,nroRegistros); 
		}
		return resultado;
	}
	
	public List<BeneficiariosVo> getBeneficiarios() {
		List<BeneficiariosVo> resultado = null;
		resultado = beneficiarioDao.getBeneficiarios(); 
		return resultado;
	}
}
