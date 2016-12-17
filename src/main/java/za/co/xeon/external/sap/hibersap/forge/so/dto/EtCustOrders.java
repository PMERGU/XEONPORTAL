package za.co.xeon.external.sap.hibersap.forge.so.dto;

import org.hibersap.annotations.BapiStructure;
import java.util.Date;
import org.hibersap.annotations.Parameter;
import java.lang.Override;

@BapiStructure
public class EtCustOrders
{

   @Parameter("AUDAT")
   Date _audat;
   @Parameter("BSTKD")
   String _bstkd;
   @Parameter("FKSTK")
   String _fkstk;
   @Parameter("DBELN")
   String _dbeln;
   @Parameter("DRZET")
   Date _drzet;
   @Parameter("FKDAT")
   Date _fkdat;
   @Parameter("PARVW_AG")
   String _parvwAg;
   @Parameter("PODAT")
   Date _podat;
   @Parameter("ERZET")
   Date _erzet;
   @Parameter("PDSTK")
   String _pdstk;
   @Parameter("VBELN")
   String _vbeln;
   @Parameter("POTIM")
   Date _potim;
   @Parameter("ERNAM")
   String _ernam;
   @Parameter("PARVW_WE")
   String _parvwWe;
   @Parameter("AUART")
   String _auart;
   @Parameter("PARVW_YE")
   String _parvwYe;
   @Parameter("PARVW_YC")
   String _parvwYc;
   @Parameter("PARVW_YH")
   String _parvwYh;
   @Parameter("SKUNNR")
   String _skunnr;

   public Date get_audat()
   {
      return this._audat;
   }

   public void set_audat(final Date _audat)
   {
      this._audat = _audat;
   }

   public String get_bstkd()
   {
      return this._bstkd;
   }

   public void set_bstkd(final String _bstkd)
   {
      this._bstkd = _bstkd;
   }

   public String get_fkstk()
   {
      return this._fkstk;
   }

   public void set_fkstk(final String _fkstk)
   {
      this._fkstk = _fkstk;
   }

   public String get_dbeln()
   {
      return this._dbeln;
   }

   public void set_dbeln(final String _dbeln)
   {
      this._dbeln = _dbeln;
   }

   public Date get_drzet()
   {
      return this._drzet;
   }

   public void set_drzet(final Date _drzet)
   {
      this._drzet = _drzet;
   }

   public Date get_fkdat()
   {
      return this._fkdat;
   }

   public void set_fkdat(final Date _fkdat)
   {
      this._fkdat = _fkdat;
   }

   public String get_parvwAg()
   {
      return this._parvwAg;
   }

   public void set_parvwAg(final String _parvwAg)
   {
      this._parvwAg = _parvwAg;
   }

   public Date get_podat()
   {
      return this._podat;
   }

   public void set_podat(final Date _podat)
   {
      this._podat = _podat;
   }

   public Date get_erzet()
   {
      return this._erzet;
   }

   public void set_erzet(final Date _erzet)
   {
      this._erzet = _erzet;
   }

   public String get_pdstk()
   {
      return this._pdstk;
   }

   public void set_pdstk(final String _pdstk)
   {
      this._pdstk = _pdstk;
   }

   public String get_vbeln()
   {
      return this._vbeln;
   }

   public void set_vbeln(final String _vbeln)
   {
      this._vbeln = _vbeln;
   }

   public Date get_potim()
   {
      return this._potim;
   }

   public void set_potim(final Date _potim)
   {
      this._potim = _potim;
   }

   public String get_ernam()
   {
      return this._ernam;
   }

   public void set_ernam(final String _ernam)
   {
      this._ernam = _ernam;
   }

   public String get_parvwWe()
   {
      return this._parvwWe;
   }

   public void set_parvwWe(final String _parvwWe)
   {
      this._parvwWe = _parvwWe;
   }

   public String get_auart()
   {
      return this._auart;
   }

   public void set_auart(final String _auart)
   {
      this._auart = _auart;
   }

   public String get_parvwYe()
   {
      return this._parvwYe;
   }

   public void set_parvwYe(final String _parvwYe)
   {
      this._parvwYe = _parvwYe;
   }

   public String get_parvwYc()
   {
      return this._parvwYc;
   }

   public void set_parvwYc(final String _parvwYc)
   {
      this._parvwYc = _parvwYc;
   }

   public String get_parvwYh()
   {
      return this._parvwYh;
   }

   public void set_parvwYh(final String _parvwYh)
   {
      this._parvwYh = _parvwYh;
   }

   public String get_skunnr()
   {
      return this._skunnr;
   }

   public void set_skunnr(final String _skunnr)
   {
      this._skunnr = _skunnr;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (_audat != null)
         result += "_audat: " + _audat;
      if (_bstkd != null && !_bstkd.trim().isEmpty())
         result += ", _bstkd: " + _bstkd;
      if (_fkstk != null && !_fkstk.trim().isEmpty())
         result += ", _fkstk: " + _fkstk;
      if (_dbeln != null && !_dbeln.trim().isEmpty())
         result += ", _dbeln: " + _dbeln;
      if (_drzet != null)
         result += ", _drzet: " + _drzet;
      if (_fkdat != null)
         result += ", _fkdat: " + _fkdat;
      if (_parvwAg != null && !_parvwAg.trim().isEmpty())
         result += ", _parvwAg: " + _parvwAg;
      if (_podat != null)
         result += ", _podat: " + _podat;
      if (_erzet != null)
         result += ", _erzet: " + _erzet;
      if (_pdstk != null && !_pdstk.trim().isEmpty())
         result += ", _pdstk: " + _pdstk;
      if (_vbeln != null && !_vbeln.trim().isEmpty())
         result += ", _vbeln: " + _vbeln;
      if (_potim != null)
         result += ", _potim: " + _potim;
      if (_ernam != null && !_ernam.trim().isEmpty())
         result += ", _ernam: " + _ernam;
      if (_parvwWe != null && !_parvwWe.trim().isEmpty())
         result += ", _parvwWe: " + _parvwWe;
      if (_auart != null && !_auart.trim().isEmpty())
         result += ", _auart: " + _auart;
      if (_parvwYe != null && !_parvwYe.trim().isEmpty())
         result += ", _parvwYe: " + _parvwYe;
      if (_parvwYc != null && !_parvwYc.trim().isEmpty())
         result += ", _parvwYc: " + _parvwYc;
      if (_parvwYh != null && !_parvwYh.trim().isEmpty())
         result += ", _parvwYh: " + _parvwYh;
      if (_skunnr != null && !_skunnr.trim().isEmpty())
         result += ", _skunnr: " + _skunnr;
      return result;
   }
}