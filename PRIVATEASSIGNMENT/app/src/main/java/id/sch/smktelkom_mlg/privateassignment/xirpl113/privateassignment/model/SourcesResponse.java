package id.sch.smktelkom_mlg.privateassignment.xirpl113.privateassignment.model;

import java.util.List;

/**
 * Created by Fay on 30/04/2017.
 */

public class SourcesResponse
{
    //    "status": "ok",
    //    "sources": [{
    //            "id": "abc-news-au",
    //            "name": "ABC News (AU)",
    //            "description": "Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.",
    //            "url": "http://www.abc.net.au/news",
    //            "category": "general",
    //            "language": "en",
    //            "country": "au",
    //            "urlsToLogos": {
    //                "small": "",
    //                "medium": "",
    //                "large": ""
    //            },
    //            "sortBysAvailable": ["top"]
    //            },]

    public String status;
    public List<Source> sources;

}
