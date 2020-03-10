package com.entreprenews.opensource.Extractors

import com.entreprenews.opensource.Models.ScrappedData
import org.openqa.selenium.chrome.ChromeDriver

interface Scrapper {
    var tiles: List<String>
    var links: List<String>
    var images: List<String>

    fun execute(): ScrappedData
    fun fetchLinks(): List<String>
    fun fetchTiles(): List<String>
    fun fetchImages(): List<String>

}