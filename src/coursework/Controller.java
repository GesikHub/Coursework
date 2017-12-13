package coursework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import equation.Equations;
import equation.equationException.AgreeFunction;
import equation.equationException.NoRootException;
import function.DataFunction;
import function.FuctionLinearInterpolation;
import function.Function;
import function.FunctionLangrandzh;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import point.Point;
import point.ArrayPoint;


public class Controller implements Initializable{
	  private ObservableList<Point> observableList = FXCollections.observableArrayList();
	  private ArrayPoint points = new ArrayPoint();
	  private ObservableList<Point> observableList2 = FXCollections.observableArrayList();
	  private ArrayPoint points2 = new ArrayPoint();
	  Equations eq;
	  DataFunction function;
	  DataFunction function1;
	  
	  private void showInputXDialog() {
	        TextInputDialog dialog = new TextInputDialog();
	        
	        dialog.setTitle("Point");
	        dialog.setHeaderText("Enter point:");
	        dialog.setContentText("X:");
	 
	        Optional<String> result = dialog.showAndWait();
	        
	        Double y = Double.valueOf(showInputYDialog());
	        Double x = null;
	        if(result.isPresent()) {
	        	x = Double.valueOf(result.get());
	        }
	        points.addPoint(new Point(x, y));
	  }
	  
	  private void showInputSecondXDialog() {
	        TextInputDialog dialog = new TextInputDialog();
	        
	        dialog.setTitle("Point");
	        dialog.setHeaderText("Enter point:");
	        dialog.setContentText("X:");
	 
	        Optional<String> result = dialog.showAndWait();
	        
	        Double y = Double.valueOf(showInputYDialog());
	        Double x = null;
	        if(result.isPresent()) {
	        	x = Double.valueOf(result.get());
	        }
	        points2.addPoint(new Point(x, y));
	  }
	  
	  private String showInputYDialog() {
	        TextInputDialog dialog = new TextInputDialog();
	        
	        dialog.setTitle("Point");
	        dialog.setHeaderText("Enter point:");
	        dialog.setContentText("Y:");
	 
	        Optional<String> result = dialog.showAndWait();
	        
	        if(result.isPresent()) {
	        	return result.get();
	        }
			return null;
	  }
	  
	  
    public static FileChooser getFileSave(String title, String typle) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(typle + "-файлы (*." + typle, "*." + typle));
        fileChooser.setTitle(title);
        return fileChooser;
    }
	  
    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSon-файлы (*.json", "*.json"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Все файлы (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }
	
	//Name first column
	@FXML TableView <Point> firstGraph;
	@FXML TableColumn<Point, Double> firstX;
	@FXML TableColumn<Point, Double> firstY;
	
	//Name second column
	@FXML TableView <Point> secondGraph;
	@FXML TableColumn<Point, Double> secondX;
	@FXML TableColumn<Point, Double> secondY;
	
	//Name FirstContext
	@FXML ContextMenu firstContex;
	
	//Name SecondContext
	@FXML ContextMenu SecondContex;
	
	//Graphik
	private final Axis xAxis = new NumberAxis();
	private final Axis yAxis = new NumberAxis();
	
	@FXML LineChart<Double, Double> graphicLineChart = new LineChart<Double, Double>(xAxis, yAxis);
	
	//Build
	@FXML Button buildButton;
	@FXML TextField start;
	@FXML TextField finish;
	@FXML TextField eps;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		firstGraph.setPlaceholder(new Label(""));
		secondGraph.setPlaceholder(new Label(""));
		updateFirstTable();
		updateSecondTable();
	}	
	
	@FXML private void doExit(ActionEvent event) {
        Platform.exit();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML public void button(ActionEvent event) {
		graphicLineChart.getData().clear();
		XYChart.Series series = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series3 = new XYChart.Series();
		XYChart.Series series4 = new XYChart.Series();
		series.setName("Function1");
		series2.setName("Function2");
		ObservableList<XYChart.Data> pointsData = FXCollections.observableArrayList();
		ObservableList<XYChart.Data> pointsData2 = FXCollections.observableArrayList();
		ObservableList<XYChart.Data> pointsData3 = FXCollections.observableArrayList();
		ObservableList<XYChart.Data> pointsData4 = FXCollections.observableArrayList();
		Double a = Double.parseDouble(start.getText());
		Double b = Double.parseDouble(finish.getText());
		Double e = Double.parseDouble(eps.getText());
		function = new FunctionLangrandzh(points);
		function1 = new FuctionLinearInterpolation(points2);
		if(a < points2.get(0).getX()) {
			TextInputDialog dialog = new TextInputDialog(null);
			dialog.setTitle("a");
			dialog.setHeaderText("а не может быть меньше " +  points2.get(0).getX() + "\nВедите коректное значение а\n");
			dialog.setContentText("Name:");
			Optional<String> result = dialog.showAndWait();
			if(result.isPresent()) {
	        	a = Double.valueOf(result.get());
	        	start.setText(String.valueOf(result.get()));
	        }
			return;
		}
		if(b > points2.get(points2.count() - 1).getX()) {
			TextInputDialog dialog = new TextInputDialog(null);
			dialog.setTitle("a");
			dialog.setHeaderText("b не может быть больше " +  points2.get(points2.count() - 1).getX() + "\nВедите коректное значение b\n");
			dialog.setContentText("Name:");
			Optional<String> result = dialog.showAndWait();
			if(result.isPresent()) {
	        	a = Double.valueOf(result.get());
	        	start.setText(String.valueOf(result.get()));
	        }
			return;
		}
		eq = new Equations(function, function1);
		try {
			eq.root(a, b, e);
			System.out.println(eq.toString());
		} catch (AgreeFunction e1) {
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("");
	        alert.setHeaderText("Данный функции совпадают");
	        alert.showAndWait();
		} catch (NoRootException e1) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("");
	        alert.setHeaderText("Данный функции не имеют корней");
	        alert.showAndWait();
		}
		for(double i = a; i <= b; i += 0.01) {
			 pointsData.add(new XYChart.Data(i, function.f(i)));
		}
		for(double i = a; i <= b; i += 0.01) {
			 pointsData2.add(new XYChart.Data(i, function1.f(i)));
		}
		for(int i = 0; i < points.count(); i ++) {
			if(points.get(i).getX() >= a && points.get(i).getX() <= b) {
				pointsData3.add(new XYChart.Data(points.get(i).getX(), points.get(i).getY()));
			}
		}
		for(int i = 0; i < points.count(); i ++) {
			if(points.get(i).getX() >= a && points.get(i).getX() <= b) {
				pointsData4.add(new XYChart.Data(points.get(i).getX(), points.get(i).getY()));
			}
		}
		series.setData(pointsData);
		series2.setData(pointsData2);
		series3.setData(pointsData3);
		series4.setData(pointsData4);
	    graphicLineChart.getData().addAll(series, series2, series3, series4);
		graphicLineChart.getStylesheets().add("coursework/mystyle.css");
	    pointsData3.forEach(data->{
            Node node = data.getNode();
            Tooltip tooltip = new Tooltip('('+data.getXValue().toString()+';'+data.getYValue().toString()+')');
            Tooltip.install(node, tooltip);
        });
	    pointsData4.forEach(data->{
            Node node = data.getNode();
            Tooltip tooltip = new Tooltip('('+data.getXValue().toString()+';'+data.getYValue().toString()+')');
            Tooltip.install(node, tooltip);
        });
	}
	@FXML private void addNewTable(ActionEvent event) {
	       FileChooser fileChooser = getFileChooser("Открыть Json-файл");
	       File file;
	        if ((file = fileChooser.showOpenDialog(null)) != null) {
	        	function = new FunctionLangrandzh();
	    		function.setValuesToJSON(file.getName());
	    		points.clear();
	    		points = function.getValues();
	    		updateFirstTable();
	        }
	}
	
	@FXML private void addNewTable2(ActionEvent event) {
	       FileChooser fileChooser = getFileChooser("Открыть Json-файл");
	       File file;
	        if ((file = fileChooser.showOpenDialog(null)) != null) {
	        	function1 = new FuctionLinearInterpolation();
	    		function1.setValuesToJSON(file.getName());
	    		points2.clear();
	    		points2 = function1.getValues();
	    		updateSecondTable();
	        }
	}
	
	private void toImage(String name) {
        String pngName = "photo" + name + ".png";
        WritableImage writableImage = new WritableImage((int)graphicLineChart.getWidth(), (int)graphicLineChart.getHeight());
        graphicLineChart.snapshot(null, writableImage);
        File file = new File("src/" + pngName);
        try{
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
        }catch (IOException e) {
        	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
 	        alert.setTitle("");
 	        alert.setHeaderText("Ошибка! Не удалось создать изображение");
 	        alert.showAndWait();
        }
    }
	
    @FXML public void doSaveHTML(ActionEvent actionEvent) {
        FileChooser fileChooser = getFileSave("Сохранить Результат", "html");
        File file;
        if ((file = fileChooser.showSaveDialog(null)) != null) {
        	 try (PrintWriter out = new 
        			 PrintWriter(new OutputStreamWriter(new FileOutputStream(file.getName()), "UTF-8"))) {
        		 out.printf("<html>%n");
        		 out.printf("<head>%n");
        		 out.printf("<head>%n");
                 out.printf("<meta http-equiv='Content-Type' content='text/html; " + 
                            "charset=UTF-8'>%n");
                 out.printf("</head>%n");
                 out.printf("<body>%n");
                 out.printf("<h2>Звіт</h2>%n");
                 out.printf("<p>У результаті розв\'язання рівняння f(x) - g(x) = 0" +
                         "з такими вихідними даними:</p>%n");
                 out.printf("<h4>Дані для функції f(x)<span style='font-family:Times, Serif;'>" + 
                         "<em>f(x)</em></span></h4>%n");
	              out.printf("<table border = '1' cellpadding=4 cellspacing=0>%n");
	              out.printf("<tr>%n");
	              out.printf("<th>X</th>%n");
	              out.printf("<th>Y</th>%n");
	              out.printf("</tr>%n");
	              out.printf("<td>%n");
	              for (int i = 0; i < function.getValues().count(); i++) {
	                  out.printf("<tr>%n");
	                  out.printf("<td>8.3f</td>", function.getValues().get(i).getX());
	                  out.printf("<td>8.3f</td>%n", function.getValues().get(i).getY());
	                  out.printf("</tr>%n");
	              }
	              out.printf("</table>%n");
	              out.printf("<h4>Дані для функції g(x)<span style='font-family:Times, Serif;'>" + 
	                         "<em>t(x)</em></span></h4>%n");
		          out.printf("<table border = '1' cellpadding=4 cellspacing=0>%n");
	              out.printf("<tr>%n");
	              out.printf("<th>X</th>%n");
	              out.printf("<th>Y</th>%n");
	              out.printf("</tr>%n");
	              out.printf("<td>%n");
	              for (int i = 0; i < function1.getValues().count(); i++) {
	                  out.printf("<tr>%n");
	                  out.printf("<td>%8.3f</td>", function1.getValues().get(i).getX());
	                  out.printf("<td>%8.3f</td>%n", function1.getValues().get(i).getY());
	                  out.printf("</tr>%n");
	              }
	             out.printf("</table>%n");
	             switch (eq.getRoots().count()) {
	                case 0:
	                    out.printf("<p>було встановлено, що рівняння не має коренів.%n</p>");
	                    break;
	                default: 
	                	if(eq.getRoots().get(0).getX() == null) {
	                		out.printf("<p>було встановлено, що рівняння має безліч коренів.%n</p>");
	                	}
	                    out.printf("<p>були отримані такі корені:</p>%n");
	                    out.printf("<table border = '1' cellpadding=4 cellspacing=0>%n");
			            out.printf("<tr>%n");
			            out.printf("<th>X</th>%n");
			            out.printf("<th>Y</th>%n");
			            out.printf("</tr>%n");
			            out.printf("<td>%n");
	                    for(int i = 0; i < eq.getRoots().count(); i++)
	                    {
	                    	  out.printf("<tr>%n");
	    	                  out.printf("<td>%8.3f</td>",  eq.getRoots().get(i).getX());
	    	                  out.printf("<td>%8.3f</td>%n",  eq.getRoots().get(i).getX());
	    	                  out.printf("</tr>%n");
	                    }
	             }
	             out.printf("</table>%n");
	             toImage("new");
	             out.printf("<img src = \"" +  "src/photonew.png" + "\"/>");
                 out.printf("</body>%n");
                 out.printf("</html>%n");
                 
        	 }  catch (IOException e) {
             }
        }
    }
    
    @FXML public void doSavePDF(ActionEvent actionEvent) {
    	FileChooser fileChooser = getFileSave("Сохранить Результат", "pdf");
    	File file;
        if ((file = fileChooser.showSaveDialog(null)) != null)
        {
        	Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        	try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath()));
	        	document.open();
	        	Anchor anchorTarget = new Anchor("Звіт");
	            anchorTarget.setName("BackToTop");
	            Paragraph title1 = new Paragraph("У результаті розв\'язання рівняння f(x) - g(x) = 0 з такими вихідними даними:", 
			    		FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD));
	            title1.setSpacingBefore(50);
	            title1.add(anchorTarget);
	            Chapter chapter1 = new Chapter(title1, 1);
	            chapter1.setNumberDepth(0);
	            document.add(chapter1);
	            
	            Paragraph title2 = new Paragraph("Дані для функції f(x)", 
	            		 FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
	            Section section1 = chapter1.addSection(title2);;
	            PdfPTable f1 = new PdfPTable(2); 
	            f1.setSpacingBefore(25);
	            f1.setSpacingAfter(25);
	            PdfPCell c1 = new PdfPCell(new Phrase("X"));  
	            f1.addCell(c1);
	            PdfPCell c2 = new PdfPCell(new Phrase("Y"));
	            f1.addCell(c2);
	            for (int i = 0; i < function.getValues().count(); i++) {
	            	  f1.addCell(function.getValues().get(i).getX().toString());
	                  f1.addCell(function.getValues().get(i).getY().toString());
	            }
	            section1.add(f1);
	            
	            Paragraph title3 = new Paragraph("Дані для функції g(x)", 
	            		 FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
	            Section section2 = chapter1.addSection(title3);
	            PdfPTable f2 = new PdfPTable(2); 
	            f2.setSpacingBefore(25);
	            f2.setSpacingAfter(25);
	            PdfPCell c3 = new PdfPCell(new Phrase("X"));  
	            f2.addCell(c3);
	            PdfPCell c4 = new PdfPCell(new Phrase("Y"));
	            f2.addCell(c4);
	            for (int i = 0; i < function1.getValues().count(); i++) {
	            	  f2.addCell(function1.getValues().get(i).getX().toString());
	                  f2.addCell(function1.getValues().get(i).getY().toString());
	            }
	            section2.add(f2);
	            
	            Paragraph title4;
	            Section section3;
	            switch (eq.getRoots().count()) {
                case 0:
                	title4 =new Paragraph("було встановлено, що рівняння не має коренів.", 
                			FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
    	            section3 = chapter1.addSection(title4);
                    break;
                default: 
                	if(eq.getRoots().get(0).getX() == null) {
                		title4 =new Paragraph("було встановлено, що рівняння має безліч коренів.", 
                    			FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
                		section3 = chapter1.addSection(title4);
                	}
                	else {
                		title4 =new Paragraph("були отримані такі корені:", 
                    			FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
                		section3 = chapter1.addSection(title4);
         	            PdfPTable rez = new PdfPTable(2); 
         	            rez.setSpacingBefore(25);
         	            rez.setSpacingAfter(25);
         	            PdfPCell c5 = new PdfPCell(new Phrase("X"));  
         	            rez.addCell(c5);
         	            PdfPCell c6 = new PdfPCell(new Phrase("Y"));
         	            rez.addCell(c6);
	                    for(int i = 0; i < eq.getRoots().count(); i++)
	                    {
	                    	rez.addCell(eq.getRoots().get(i).getX().toString());
	                    	rez.addCell(eq.getRoots().get(i).getY().toString());
	                    }
	     	            section3.add(rez);
                	}
	            }
	            
	            toImage("new");
	            Paragraph title5 = new Paragraph("График", 
	            		 FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
	            Section section4 = chapter1.addSection(title2);;
	            Image image2;
				try {
					image2 = Image.getInstance("src/photonew.png");
		            image2.scaleAbsolute(420f, 420f);
		            section4.add(image2);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            
	            document.add(section1);
	            document.add(section2);
	            document.add(section3);
	            document.add(section4);
	            document.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
        }
    }
	
	@FXML private void updateX(TableColumn.CellEditEvent<Point, Double> t) {
	    Point p = (Point) t.getTableView().getItems().get(t.getTablePosition().getRow());
	    p.setX(t.getNewValue());
	    sortX();
	}
	@FXML private void updateY(TableColumn.CellEditEvent<Point, Double> t) {
	    Point p = (Point) t.getTableView().getItems().get(t.getTablePosition().getRow());
	    p.setY(t.getNewValue());
	    sortX();
	}
	
	@FXML private void addNewElement1(ActionEvent event) {
		showInputXDialog();
        updateFirstTable();
	}
	
	@FXML private void addNewElement2(ActionEvent event) {
		showInputSecondXDialog();
        updateSecondTable();
	}
	
	private void sortX() {
		updateData();
		points.sort();
		updateFirstTable();
	}
	
	private void sortSecondX() {
		updateSecondData();
		points2.sort();
		updateSecondTable();
	}
	
	private void updateData() {
		points.clear();
		for(Point point : observableList) {
			points.addPoint(point);
		}
	}
	
	private void updateSecondData() {
		points2.clear();
		for(Point point : observableList2) {
			points2.addPoint(point);
		}
	}
	
	private void updateSecondTable() {
		secondGraph.setEditable(true);
		secondY.setSortable(false);
	    List<Point> list = new ArrayList<>();
	    for (int i = 0; i < points2.count(); i++) {
	        list.add(points2.get(i));
	    }
	    observableList2 = FXCollections.observableList(list);
	    secondGraph.setItems(observableList2);
	    
	    secondX.setCellValueFactory(new PropertyValueFactory<Point, Double>("X"));
	    secondX.setCellFactory(TextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
	    secondX.setOnEditCommit(t -> updateX(t));
	    
	    secondY.setCellValueFactory(new PropertyValueFactory<Point, Double>("Y"));
	    secondY.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
	    secondY.setOnEditCommit(t -> updateY(t));
	}
	
	private void updateFirstTable() {
		firstGraph.setEditable(true);
		firstY.setSortable(false);
	    List<Point> list = new ArrayList<>();
	    for (int i = 0; i < points.count(); i++) {
	        list.add(points.get(i));
	    }
	    observableList = FXCollections.observableList(list);
	    firstGraph.setItems(observableList);
	    
	    firstX.setCellValueFactory(new PropertyValueFactory<Point, Double>("X"));
	    firstX.setCellFactory(TextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
	    firstX.setOnEditCommit(t -> updateX(t));
	    
	    firstY.setCellValueFactory(new PropertyValueFactory<Point, Double>("Y"));
	    firstY.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
	    firstY.setOnEditCommit(t -> updateY(t));
	}
}
