package ru.liga.homework1.outputs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

import ru.liga.homework1.exceptions.InvalidOutputParametersException;
import ru.liga.homework1.model.CurrencyData;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Вывод результата в виде графика
 */
public class GraphOutput implements Output {
    @Override
    public void doOutput(List<CurrencyData> predictionResult) throws InvalidOutputParametersException {
        if (predictionResult == null || predictionResult.size() == 0)
            throw new InvalidOutputParametersException("Нет данных");

        ExchangeRateChart graph = new ExchangeRateChart(createPanel(predictionResult));
        graph.pack();
        UIUtils.centerFrameOnScreen(graph);
        graph.setVisible(true);
    }

    private final static String title = "Currency exchange rate prediction";
    static class ExchangeRateChart extends ApplicationFrame {
        public ExchangeRateChart(ChartPanel chartPanel) {
            super(title);
            setContentPane(chartPanel);
        }
    }

     private static ChartPanel createPanel(List<CurrencyData> predictionResult) {
         JFreeChart chart = createChart(createDataset(predictionResult));
         ChartPanel panel = new ChartPanel(chart, false);
         panel.setFillZoomRectangle(true);
         panel.setMouseWheelEnabled(true);
         return panel;
        }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(title, "Date", "Currency exchange rate", dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd.MM.yyyy"));
        return chart;
    }

    private static XYDataset createDataset(List<CurrencyData> predictionResult) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        for (var currencyData : predictionResult)
            dataset.addSeries(createSeries(currencyData));
        return dataset;
    }

    private static TimeSeries createSeries(CurrencyData currencyData) {
        TimeSeries series = new TimeSeries(currencyData.getCurrency().name());
        for (var rate : currencyData.getData()) {
            var date = rate.getDate();
            series.add(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()), rate.getRate());
        }
        return series;
    }
}
