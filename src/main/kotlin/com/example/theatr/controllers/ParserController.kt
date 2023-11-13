package com.example.theatr.controllers

import com.example.theatr.entity.Banner
import com.example.theatr.entity.Description
import com.example.theatr.entity.Product
import com.example.theatr.service.BannerService
import com.example.theatr.service.ProductService
import com.example.theatr.service.UserService
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File


@RestController
@RequestMapping("/parser")
class ParserController {
    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var bannerService: BannerService

    val log: Logger = LoggerFactory.getLogger(this::class.java)


    @GetMapping("/getAllProducts")
    fun getAllProducts(firstId: Int): List<Product> {
        log.info("/getAllProducts")
        return productService.getAllProduct(firstId)
    }

    @GetMapping("/getProductByName")
    fun getProductByName(name: String): Product {
        log.info("/getProductByName")
        return productService.getProductByName(name)
    }

    @GetMapping("/getAllBanner")
    fun getAllPBanner(): List<Banner> {
        log.info("/getAllBanner")
        return bannerService.getAllBanner()
    }

    @GetMapping("/saveBanner")
    fun saveBanner(img: String, txt: String){
        log.info("/saveBanner")
        bannerService.saveBanner(Banner(img, txt))
    }


    @GetMapping("/products")
    fun getProducts(): List<Product> {
        log.info("/products")

        val url = "https://skin.land/market/dota2/?hold=2%2C8&page="
        var doc: Document = Jsoup.connect(url).get()

        val pagesCountElements = doc.getElementsByClass("page-item").count() - 2
        val products = mutableListOf<Product>()

        for (i in 1..pagesCountElements) {
            doc = Jsoup.connect(url + i.toString()).get()
            val productElements: Elements = doc.getElementsByClass("skin-card")


            for (element in productElements) {
                val name = element.getElementsByClass("skin-card__name").text()
                val price = element.getElementsByClass("skin-card__skins-price").text()
                val itemUrl = element.getElementsByClass("skin-card").attr("href")
                val imageUrl = element.getElementsByAttribute("data-src").attr("data-src")
                val lotCount = element.getElementsByClass("skin-card__skins-count").text().filter { it.isDigit() }.toInt()
                val product = Product(name, price, itemUrl, imageUrl, lotCount)
                products.add(product)
                productService.saveProduct(product)
            }
        }

        var rows = mutableListOf(listOf("Название", "Ссылка на товар", "Цена", "Ссылка на изображение"))
        for (i in products) {
            rows.add(listOf(i.name, i.itemUrl, i.price, i.imageUrl))
        }

        csvWriter().writeAll(rows, File("test.csv").outputStream())

        return products
    }

    @GetMapping("/productDescription")
    fun productDescription(login: String, password: String, productUrl: String): Description {
        log.info("/productDescription")

        val url = "productUrl"
        val doc: Document = Jsoup.connect(url).get()

        val pagesElements = doc.getElementsByClass("skin-page__basic-data-cell")

        for (element in pagesElements) {
            val type = pagesElements.get(0).text()
            val rarity = pagesElements.get(1).text()
            val quality = pagesElements.get(2).text()
            val hero = pagesElements.get(3).text()
            log.info(type, rarity, quality, hero)
            return Description(type, rarity, quality, hero)
        }
        return Description(null, null, null, null)

    }

    @PostMapping("/single-file-upload")
    fun handleFileUploadUsingCurl(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        log.info("single-file-upload")

        val map: Map<String, String> = HashMap()

        return ResponseEntity.ok<Any>(map)
    }

}