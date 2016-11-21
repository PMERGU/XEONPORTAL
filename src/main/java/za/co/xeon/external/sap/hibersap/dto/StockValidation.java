package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;
import java.math.BigDecimal;
import java.lang.Override;

@BapiStructure
public class StockValidation
{

   @Parameter("MEINH")
   String _meinh;
   @Parameter("MEINS")
   String _meins;
   @Parameter("VERME")
   BigDecimal _verme;
   @Parameter("CHARG")
   String _charg;
   @Parameter("MATNR")
   String _matnr;
   @Parameter("MAKTX")
   String _maktx;
   @Parameter("LGNUMT")
   String _lgnumt;

   public String get_meinh()
   {
      return this._meinh;
   }

   public void set_meinh(final String _meinh)
   {
      this._meinh = _meinh;
   }

   public String get_meins()
   {
      return this._meins;
   }

   public void set_meins(final String _meins)
   {
      this._meins = _meins;
   }

   public BigDecimal get_verme()
   {
      return this._verme;
   }

   public void set_verme(final BigDecimal _verme)
   {
      this._verme = _verme;
   }

   public String get_charg()
   {
      return this._charg;
   }

   public void set_charg(final String _charg)
   {
      this._charg = _charg;
   }

   public String get_matnr()
   {
      return this._matnr;
   }

   public void set_matnr(final String _matnr)
   {
      this._matnr = _matnr;
   }

   public String get_maktx()
   {
      return this._maktx;
   }

   public void set_maktx(final String _maktx)
   {
      this._maktx = _maktx;
   }

   public String get_lgnumt()
   {
      return this._lgnumt;
   }

   public void set_lgnumt(final String _lgnumt)
   {
      this._lgnumt = _lgnumt;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (_meinh != null && !_meinh.trim().isEmpty())
         result += "_meinh: " + _meinh;
      if (_meins != null && !_meins.trim().isEmpty())
         result += ", _meins: " + _meins;
      if (_verme != null)
         result += ", _verme: " + _verme;
      if (_charg != null && !_charg.trim().isEmpty())
         result += ", _charg: " + _charg;
      if (_matnr != null && !_matnr.trim().isEmpty())
         result += ", _matnr: " + _matnr;
      if (_maktx != null && !_maktx.trim().isEmpty())
         result += ", _maktx: " + _maktx;
      if (_lgnumt != null && !_lgnumt.trim().isEmpty())
         result += ", _lgnumt: " + _lgnumt;
      return result;
   }
}