package pl.coreservices.service;

import com.google.common.collect.Lists;
import pl.coreservices.model.web.Statistic;
import pl.coreservices.model.web.StatisticsList;

import java.util.List;

public class StatisticsService {

    public final static String ALL_NAME = "ALL";


    public StatisticsList getStatistics(String name) {
        List<Statistic> statistics;
        if(ALL_NAME.equals(name)) {
            statistics = Lists.newArrayList(new Statistic("aaa",2),
                    new Statistic("bbb", 3), new Statistic("ccc", 1));
        }
        else {
            statistics = Lists.newArrayList(new Statistic(name, 2));
        }
        return new StatisticsList(statistics);
    }

}
