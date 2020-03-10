package com.entreprenews.opensource.Extractors.Technology

import com.entreprenews.opensource.Extractors.Scrapper
import com.entreprenews.opensource.Models.ScrappedData
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

class TechCrunch(private val driver: ChromeDriver):
    Scrapper {
    override var images: List<String> =  ArrayList<String>()
    override var tiles: List<String> =  ArrayList<String>()
    override var links: List<String> =  ArrayList<String>()

    override fun execute(): ScrappedData {
        lateinit var result: ScrappedData


        links = this.fetchLinks()
//        tiles = this.fetchTiles()
//        images = this.fetchImages()

        return ScrappedData(
            links,
            tiles,
            images
        )
    }

    override fun fetchLinks(): List<String> {

        val links = driver.findElementsByClassName("river--homepage")
            .map { it.findElement(By.className("post-block--unread")) }
            .map { it.findElement(By.tagName("HEADER")) }
            .map { it.findElement(By.tagName("H2")) }
            .map { it.findElement(By.tagName("A")) }
            .map { it.getAttribute("href") }

        return links
    }

    override fun fetchTiles(): List<String> {
        val tiles =  driver.findElementsByClassName("river--homepage")
            .map { it.findElement(By.className("post-block--unread")) }
            .map { it.findElement(By.tagName("HEADER")) }
            .map { it.findElement(By.tagName("H2")) }
            .map { it.findElement(By.tagName("A")) }
            .map { it.text }

        return tiles
    }

    override fun fetchImages(): List<String> {
        val images =  driver.findElementsByClassName("pl")
            .map { it.findElement(By.className("hero")) }
            .map { it.findElement(By.tagName("IMG")) }
            .map { it.getAttribute("data-src") }

        return images
    }
}