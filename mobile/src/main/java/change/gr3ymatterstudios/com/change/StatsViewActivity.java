package change.gr3ymatterstudios.com.change;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Afzal on 12/28/14.
 */
public class StatsViewActivity extends Activity{

    XYSeries series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout view = (FrameLayout)this.findViewById(R.id.container);
        view.addView(createChart());
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new RoutineListViewFragment())
//                    .commit();
//        }



    }


    protected View createChart(){

        Random r = new Random();
        series = new XYSeries("Weight Lifted");
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Float> weight = new ArrayList<>();

        dates.add("Monday");
        dates.add("Tuesday");
        dates.add("Wednesday");
        dates.add("Thursday");
        dates.add("Friday");
        dates.add("Saturday");
        dates.add("Sunday");



        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setLineWidth(2);
        renderer.setColor(Color.BLUE);
        renderer.setDisplayBoundingPoints(true);
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setPointStrokeWidth(3);


        XYMultipleSeriesDataset multipleSeriesDataset = new XYMultipleSeriesDataset();
        multipleSeriesDataset.addSeries(series);

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00));
        mRenderer.setPanEnabled(false, false);
        mRenderer.setShowGrid(true);

        for(int i = 0; i<7;i++){
            weight.add(r.nextFloat()*(200-25) + 25);
            series.add(i,weight.get(i));
            mRenderer.addXTextLabel(i, dates.get(i));
            mRenderer.setXLabels(0);
        }

        GraphicalView chartView = ChartFactory.getLineChartView(getApplicationContext(), multipleSeriesDataset, mRenderer);

        return chartView;
    }



}
