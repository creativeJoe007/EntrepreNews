package com.entreprenews.opensource.Extractors.AfricanTech

import com.entreprenews.opensource.Extractors.Scrapper
import com.entreprenews.opensource.Models.ScrappedData
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

class TechPoint (private val driver: ChromeDriver):
    Scrapper {
    override var images: List<String> =  ArrayList<String>()
    override var tiles: List<String> =  ArrayList<String>()
    override var links: List<String> =  ArrayList<String>()

    override fun execute(): ScrappedData {
        lateinit var result: ScrappedData


        links = this.fetchLinks()
//        tiles = this.fetchTiles()
        images = this.fetchImages()

        return ScrappedData(
            links,
            tiles,
            images
        )
    }

    override fun fetchLinks(): List<String> {
        val links = driver.findElementsByClassName("ajaxify-pagination")
            .map { it.findElement(By.tagName("ARTICLE")) }
            .map { it.findElement(By.className("row")) }
            .map { it.findElement(By.className("medium-8")) }
            .map { it.findElement(By.tagName("HEADER")) }
            .map { it.findElement(By.tagName("H5")) }
            .map { it.findElement(By.tagName("A")) }
            .map { it.getAttribute("href") }

        println("links $links")

        return links
    }

    override fun fetchTiles(): List<String> {
        val tiles =  driver.findElementsByClassName("ajaxify-pagination")
            .map { it.findElement(By.tagName("ARTICLE")) }
            .map { it.findElement(By.className("row")) }
            .map { it.findElement(By.className("medium-8")) }
            .map { it.findElement(By.tagName("HEADER")) }
            .map { it.findElement(By.tagName("H5")) }
            .map { it.findElement(By.tagName("A")) }
            .map { it.text }

        return tiles
    }

    override fun fetchImages(): List<String> {
        val images =  driver.findElementsByClassName("ajaxify-pagination")
            .map { it.findElement(By.className("small-12")) }
            .map { it.findElement(By.tagName("ARTICLE")) }
            .map { it.findElement(By.className("row")) }
            .map { it.findElement(By.className("medium-4")) }
            .map { it.findElement(By.tagName("FIGURE")) }
            .map { it.findElement(By.tagName("A")) }
            .map { it.findElement(By.tagName("IMG")) }
            .map { it.getAttribute("src") }

        return images
    }
}