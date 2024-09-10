# Nesine Cucumber Mobil Test Otomasyon Framework'ü

## 1. Kurulum
Proje için gerekli temel kurulumları yapmak oldukça basit. Aşağıdaki adımları takip edin:

* Derlemeler için ``Java (JDK 17 önerilen)``, ``Maven`` ve ``Appium'un`` sisteminizde yüklü olduğundan emin olun.
* Koşumlar için önerilen IDE'lerden ``Intellij IDEA`` ve ``Android Studio`` emulatörlerinin var olduğundan emin olun.
* Allure raporlaması için de allure kurulumu gerekmektedir. Bunun için ``Allure CLI`` yükleyin.

## 2. Projeyi Başlatmadan Önce
a. Android emulatörü başlatın ve ``test_config_properties`` dosyasında ki verileri kendi cihaz veya cihazlarınıza göre düzenleyin
```properties
# MOBILE
deviceName=emulator-5554
platformName=Android
platformVersion=12.0
automationName=UiAutomator2
appPackage=com.pordiva.nesine.android
appActivity=com.nesine.ui.taboutside.splash.SplashActivity
appiumServerURL=http://127.0.0.1:4723/

# Appium
waitMillis=10000

# URL
baseUrl=https://www.nesine.com

# File Paths
userCredentials=src/test/resources/data/test/user_credentials.json
commonDataPath=src/test/resources/data/common/

```

b. ``src\test\resources\data\test\user_credentials.json`` dosyasına gidip bu dosyada <span style="color:red">**TCKN**</span> ve <span style="color:red">**PASSWORD**</span> alanlarını doldurmalısınız:

```json {
{
  "user": {
    "tckn": "x",
    "password": "z"
  }
}
```

* Appium Server'ı başlatmayı unutmayın.

## 3. Dockerize Proje (Tercihen)
Projeyi dockerize etmek isterseniz aşağıda ki adımları takip ediniz

Aşağıdaki komutla Docker imajını oluşturun:
```bash
docker build -t nesine_cucumber_mobil .
```

Docker imajını kullanarak bir konteyner çalıştırın:
```bash
docker run -d --name nesine_cucumber_mobil-container nesine_cucumber_mobil
```

## 4.Testleri Çalıştırma
### a. IntelliJ IDEA Üzerinde VM ile Çalıştırma
IntelliJ IDEA üzerinde VM aracılığıyla testleri çalıştırmak için, ekranın sağ üst köşesindeki Edit Configurations kısmına gidin. Buradan TestNG yapılandırmasını seçin ve aşağıdaki ayarları ekleyin:

Test türü (Test kind): ``Class``

Class: ``runners.Runner``

Ayrıca paralel test çalıştırma ayarları için VM options ``Test runners params`` bölümüne aşağıdaki parametreleri ekleyin:
```bash
-threadcount 5 -dataproviderthreadcount 5
```
### b. MVN ile Çalıştırma

#### b.1 Tüm Testler
```bash
mvn clean test
```

#### b.2 Tag ile Tetitklemek
Eğer belirli bir etiket ``(örneğin @smoke)`` ile işaretlenmiş senaryoları çalıştırmak istiyorsanız, Maven komutuna aşağıdaki gibi bir parametre ekleyebilirsiniz:
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

## 5. Testlerden Sonra Allure Rapor Almak

Testler çalıştıktan sonra sonuçları raporlamak için Allure'yi şu şekilde kullanabilirsiniz:
```bash
allure serve
```
Bu komut ile Allure test sonuçlarını tarayıcıda görüntüleyebilir ve detaylı raporları inceleyebilirsiniz.


## 6. Proje Detayları
### 1. DriverFactory
   DriverFactory sınıfı, testlerin mobil cihazlar ve emülatörler üzerinde çalıştırılmasını sağlayan Appium ile uyumlu bir yapı sağlar. Appium için gerekli ayarlar (cihaz adı, platform sürümü, Appium server URL'si) test_config.properties dosyasında yer almaktadır.
   Bu sınıf, WebDriver başlatma, ayarların yüklenmesi ve WebDriver'ın yönetimini sağlar. Aynı zamanda Appium'un başlatılmasını ve test bitiminde kapatılmasını da yönetir.
### 2. Konfigürasyon ve Test Verileri
   test_config.properties dosyası, Appium'a özgü ayarlar içerir. Bu ayarlarda cihaz adı, platform sürümü, Appium server URL'si gibi detaylar bulunur.
   *common/json dizini altında, her ortamda geçerli olan statik test verileri saklanır. Bu veriler ortamlardan bağımsızdır ve tüm testlerde kullanılır.
   *test/json dizini altında, yalnızca test ortamına özgü test verileri bulunmaktadır. Test ortamına bağlı olarak bu verilerle test yapılır.
### 3. Page Object Model (POM) Yapısı
   pages dizini altında, uygulamanın çeşitli bölümleriyle etkileşim kurulan Page Object Model (POM) yapısındaki sınıflar bulunur. Bu yapıda her sayfa için bir sınıf tanımlanmıştır ve bu sınıflar, sayfa üzerindeki elementlerle etkileşim kurar.
   PageFactoryManager sınıfı, POM yapısındaki sayfaların yönetimini sağlar ve ihtiyaç duyulan sayfa sınıflarını dinamik olarak üretir.
### 4. Dependency Injection ve PicoContainer
   Projede dependency injection yapısı için PicoContainer kullanılır. Bu, testlerin her aşamasında gerekli olan bağımlılıkların otomatik olarak enjekte edilmesini sağlar. Context sınıfı ile WebDriver ve diğer gerekli bileşenler testlere enjekte edilir.
### 5. Test Koşumu ve Paralel Çalıştırma
   runners dizini altında yer alan Runner sınıfı, testlerin başlatılmasını sağlar. Bu sınıf, TestNG kullanarak testlerin paralel çalışmasını sağlar ve CucumberOptions ile test etiketlerine (tags) göre filtreleme yapılabilir.
   Paralel test çalıştırma ayarları hem TestNG XML dosyasında hem de Maven Surefire Plugin'de tanımlanmıştır.
### 6. Hooks: Başlatma ve Sonlandırma İşlemleri
   MainHooks sınıfı, testler başlamadan önce ve sonra kritik aksiyonları yönetir. WebDriver başlatma, tarayıcı kapatma, test başarısız olduğunda ekran görüntüsü alma ve bu görüntülerin Allure raporlarına eklenmesi gibi işlemler burada yapılır.
   PreConditionHooks sınıfı, tekrarlayan ve genellikle testten önce yapılması gereken işlemler için kullanılır. Örneğin, API ile veri oluşturma ya da statik tekrar eden işlemleri yönetir.
### 7. Veri Yönetimi ve Global Data
   Context sınıfı, her test için WebDriver, WebDriverWait ve global veri yönetimini sağlar. Bu sınıf, test sırasında global olarak ihtiyaç duyulan verilerin saklanması ve kullanılması amacıyla tasarlanmıştır.
   GlobalData sınıfı, adım adım test verilerini saklamak ve gerektiğinde bu verilere ulaşmak için kullanılır.
### 8. Element Etkileşimleri
   Projede, uygulama içindeki elementlerle etkileşim için Selenium kullanılır. Bu sayede web elementleri kolaylıkla bulunabilir ve çeşitli aksiyonlar (tıklama, veri girme, vs.) gerçekleştirilebilir.



