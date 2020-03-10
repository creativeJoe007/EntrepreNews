package com.entreprenews.opensource

import com.entreprenews.opensource.Extractors.AfricanTech.TechPoint
import com.entreprenews.opensource.Extractors.Business.Entrepreneur
import com.entreprenews.opensource.Extractors.Business.HackerNoons
import com.entreprenews.opensource.Extractors.Scrapper
import com.entreprenews.opensource.Extractors.Technology.TechCrunch
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.chrome.ChromeDriver
import java.util.*


/*
* Just a URL extractor for upcoming entrepreneurs who would
* Like to stay updated and moltivated as they navigate this journey
 */

fun main() {
    val driver = ChromeDriver()

    openNewTab(driver, "https://hackernoon.com/tagged/startups",
        HackerNoons(driver), 1)
    openNewTab(driver, "https://www.entrepreneur.com/topic/entrepreneurship",
        Entrepreneur(driver), 2)
    openNewTab(driver, "https://techpoint.africa/",
        TechPoint(driver), 3)
    openNewTab(driver, "https://techcrunch.com/",
        TechCrunch(driver), 1)



    driver.close()
    driver.quit()
}

fun openNewTab(driver: ChromeDriver, url: String, scrappingClass: Scrapper, position: Int) {
    (driver as JavascriptExecutor).executeScript("window.open()")
    val tabs: ArrayList<String?> = ArrayList<Any?>(driver.getWindowHandles()) as ArrayList<String?>
    println("${scrappingClass}")
    driver.switchTo().window(tabs[position])
    driver.get(url)

    var executableClass = scrappingClass
    val executeNews = executableClass.execute()

    println("${scrappingClass} Links ${executeNews.links}")
    println("${scrappingClass} Tiles ${executeNews.tiles}")
    println("${scrappingClass} Images ${executeNews.images}")

}