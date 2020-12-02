/*
 * Copyright (C) 2012-13 MINHAP, Gobierno de España This program is licensed and may be used,
 * modified and redistributed under the terms of the European Public License (EUPL), either version
 * 1.1 or (at your option) any later version as soon as they are approved by the European
 * Commission. Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * more details. You should have received a copy of the EUPL1.1 license along with this program; if
 * not, you may find it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */

package es.mpt.dsic.inside.pdf;

public class PdfOptions {

  // Desplazamiento, con respecto al borde de la derecha, en que se estampa la página
  private Float pagePositionX;
  // Desplazamiento, con respecto al borde de abajo, en que se estampa la página
  private Float pagePositionY;
  // Porcentaje en que se quiere que aparezca la página
  private Float pagePercent;

  // Indica si se han de imprimir los números de página
  private Boolean printPageNumbers;
  // Indica el desplazamiento, con respecto al borde de la izquierda (si es positivo), o el borde de
  // la derecha (si es negativo)
  // en que quiere imprimirse el número de página.
  private Float pageSepHoriz;
  // Indica el desplazamiento, con respecto al borde de abajo (si es positivo), o el borde arriba
  // (si es negativo)
  // en que quiere imprimirse el número de página.
  private Float pageSepVerti;

  public static Float DEFAULT_PAGE_PERCENT = 85.0f;
  public static Float DEFAULT_PAGE_POSITION_X = 40f;
  public static Float DEFAULT_PAGE_POSITION_Y = 80f;


  public static PdfOptions createDefault() {
    PdfOptions options = new PdfOptions();
    options = new PdfOptions();
    options.setPagePercent(DEFAULT_PAGE_PERCENT);
    options.setPagePositionX(DEFAULT_PAGE_POSITION_X);
    options.setPagePositionY(DEFAULT_PAGE_POSITION_Y);

    options.setPrintPageNumbers(true);
    options.setPageSepHoriz(-50);
    options.setPageSepVerti(25);

    return options;
  }

  public float getPagePositionX() {
    return pagePositionX;
  }

  public void setPagePositionX(float pagePositionX) {
    this.pagePositionX = pagePositionX;
  }

  public float getPagePositionY() {
    return pagePositionY;
  }

  public void setPagePositionY(float pagePositionY) {
    this.pagePositionY = pagePositionY;
  }

  public float getPagePercent() {
    return pagePercent;
  }

  public void setPagePercent(float pagePercent) {
    this.pagePercent = pagePercent;
  }

  public boolean isPrintPageNumbers() {
    return printPageNumbers;
  }

  public void setPrintPageNumbers(boolean printPageNumbers) {
    this.printPageNumbers = printPageNumbers;
  }

  public float getPageSepHoriz() {
    return pageSepHoriz;
  }

  public void setPageSepHoriz(float pageSepHoriz) {
    this.pageSepHoriz = pageSepHoriz;
  }

  public float getPageSepVerti() {
    return pageSepVerti;
  }

  public void setPageSepVerti(float pageSepVerti) {
    this.pageSepVerti = pageSepVerti;
  }


}
