# Pagseguro para Android
Integração de pagseguro para android sem usar os aplicativos nativos da pagseguro

## Introdução:
A maioria dos desenvolvedores sabem que a integração do pagseguro para android ainda é algo muito "chato". A integração com os aplicativos nativos da pagseguro ou wallet(carteira) obriga os desenvolvedores a ter os aplicativos
instalados e não funcionam perfeitamente. Me aventurando nessa linha, rapidamente percebi que este caminho é muito cheios de impecílios e gerava um monte de código de validação extra.

Noentando a pagseguro é bem popular e já tem um grau de maturidade interessante. Os consumidores brasileiros se sentem a vontade pagando com ela, pois a pagseguro tem todo um processo de validação e autorização de pagamentos, além de permitir a abertura de disputas em casos de fraude. Isso lhe torna um meio de pagamento indispensável no mercado brasileiro.

Foi pensando nessa necessidade, que decidi criar uma integração completa. Este aplicativo de demostração atende às necessidades mínimas do programador e em 90% dos casos é mais do que suficiente para a maioria dos aplicativos, que desejam vender algum tipo de pŕoduto. 

## Funções do aplicativo:
<li> Oferece um modelo simples para criação de produtos de acordo com as especifiações da pagseguro</li>
<li> A criação de produtos valida as entradas a nível de programação para evitar erros durante a codificação</li>
<li> Prevê os cenários/fluxos de navegação em caso de cancelamento, sucesso e erro exibindo mensagens</li>
<li> Não requer nenhum aplicativo da pagseguro pre-instalado e realiza as transações atraves de webservices</li>
<li> Exibe progresso diretamente na actionbar e atualiza os titulos de acordo com as etapas do pagamento</li>
<li> Permite a configuração dos dados da conta do pagseguro através do xml de preferencias</li>

## Bibliotecas usadas:
Para que o exemplo funcione, usamos uma única biblioteca de REST bem popular também usada pelo intagram, Pinterest e outros chamada: Android Asynchronous Http Client. Esta biblioteca usa a licença Apache 2.0. Ela pode ser encontrada no link: http://loopj.com/android-async-http/

Ela esta sendo referenciada no build.gradle da app (Module:app)
```xml
dependencies {
...
    compile 'com.loopj.android:android-async-http:1.4.8'
...
}
```
## Exemplo de uso (super simples):
Depois que o cliente adicionou seus produtos ao seu carrinho (shoppingCart), criamos os objetos da pagseguro com a factory e efetuamos o pagamento. Neste exemplo estamos simulando uma compra simples de apenas um item (playstation)
```java
// ... 
// simulating an user buying a playstation
final PagSeguroFactory pagseguro = PagSeguroFactory.instance();
List<PagSeguroItem> shoppingCart = new ArrayList<>();
shoppingCart.add(pagseguro.item("123", "PlayStation", BigDecimal.valueOf(3.50), 1, 300));
PagSeguroPhone buyerPhone = pagseguro.phone(PagSeguroAreaCode.DDD81, "998187427");
PagSeguroBuyer buyer = pagseguro.buyer("Ricardo Ferreira", "14/02/1978", "15061112000", "test@email.com.br", buyerPhone);
PagSeguroAddress buyerAddress = pagseguro.address("Av. Boa Viagem", "51", "Apt201", "Boa Viagem", "51030330", "Recife", PagSeguroBrazilianStates.PERNAMBUCO);
PagSeguroShipping buyerShippingOption = pagseguro.shipping(PagSeguroShippingType.PAC, buyerAddress);
PagSeguroCheckout checkout = pagseguro.checkout("Ref0001", shoppingCart, buyer, buyerShippingOption);
// starting payment process
new PagSeguroPayment(MainActivity.this).pay(checkout.buildCheckoutXml());
// ...
```

## Preferências Pagseguro
O arquivo de preferências se chama: <b>pagseguro.xml</b><br/> 
<b><font color="red">IMPORTANTE:</font></b> Antes de rodar o exemplo, certifique-se que você substituiu as preferências abaixo pelas suas. Caso contrário o exemplo não irá funcionar! Este exemplo esta configurado para rodar em modo <b>SANDBOX</b>, ou seja, não será realizada nenhuma transação de "verdade". Após seus testes, basta substituir as url's de sandbox, pelas reais.
```xml
<resources>
    <!-- we are using pagseguro's sandbox values here. -->
    <!-- you can use your onw values and after tests, use the real values -->
    <string name="pagseguro_vendor_email">yourPagSeguroEmail</string>
    <string name="pagseguro_vendor_token">yourPagSeguroToken</string>
    <string name="pagseguro_webservice_checkout_address" formatted="false">"https://ws.sandbox.pagseguro.uol.com.br/v2/checkout?email=%s&amp;token=%s"</string>
    <string name="pagseguro_payment_page">"https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=%s"</string>
</resources>
```

## Imagens:
<img src="http://3.bp.blogspot.com/-3lXx-ZQnaPk/VdZlwsX0_3I/AAAAAAAACC0/j46MNNCWnC0/s1600/Screenshot_2015-08-20-07-51-38.png" alt="1"  style="width:100px;height:177px;">
<img src="http://2.bp.blogspot.com/-ZOUn2wNzvho/VdZlwyFEdzI/AAAAAAAACC8/yQWxuGYijEs/s1600/Screenshot_2015-08-20-07-51-52.png" alt="2"  style="width:100px;height:177px;">
<img src="http://4.bp.blogspot.com/-TSsN_4Vjkak/VdZlxQNokbI/AAAAAAAACDI/Ze-0oUkRYhc/s1600/Screenshot_2015-08-20-07-52-06.png" alt="3"  style="width:100px;height:177px;">
<img src="http://2.bp.blogspot.com/-JZNHY7Oon9M/VdZlyHzP6-I/AAAAAAAACDY/fWexLKjGbdI/s1600/Screenshot_2015-08-20-07-52-36.png" alt="4"  style="width:100px;height:177px;">
<img src="http://1.bp.blogspot.com/-CKvNa8ETzO4/VdZlyEYGqxI/AAAAAAAACDU/yTgTjKQR7f0/s1600/Screenshot_2015-08-20-13-01-12.png" alt="5"  style="width:100px;height:177px;">
<img src="http://4.bp.blogspot.com/-5H0N-VwtElc/VdZlyUCvgcI/AAAAAAAACDc/CZH8WffZI8A/s1600/Screenshot_2015-08-20-13-01-45.png" alt="6"  style="width:100px;height:177px;">
<img src="http://2.bp.blogspot.com/-1OsWP6RusI4/VdZly4YHjKI/AAAAAAAACDo/mkNPtyByC8Y/s1600/Screenshot_2015-08-20-13-02-13.png" alt="7"  style="width:100px;height:177px;">
<img src="http://4.bp.blogspot.com/-ZQ6Wnuu6Flk/VdZlzNCgrPI/AAAAAAAACDw/lOJDm2MhKGY/s1600/Screenshot_2015-08-20-13-02-25.png" alt="8"  style="width:100px;height:177px;">
<img src="http://2.bp.blogspot.com/-IzBh9rgfBro/VdZlzAZM5hI/AAAAAAAACD0/xghKW9ndU70/s1600/Screenshot_2015-08-20-13-02-31.png" alt="9"  style="width:100px;height:177px;">
<img src="http://4.bp.blogspot.com/-RL4DZBl6BIE/VdZlzkovC3I/AAAAAAAACD8/VIvwsAQHFXc/s1600/Screenshot_2015-08-20-13-02-36.png" alt="10"  style="width:100px;height:177px;">
<img src="http://3.bp.blogspot.com/-8IZrEW7Atnc/VdZlz208VRI/AAAAAAAACEI/5dq5ARohggw/s1600/Screenshot_2015-08-20-13-02-57.png" alt="11"  style="width:100px;height:177px;">
<img src="http://3.bp.blogspot.com/-Qpd2pzLuebo/VdZl0TZfW2I/AAAAAAAACEQ/X5CxzIDDIh4/s1600/Screenshot_2015-08-20-13-03-08.png" alt="12"  style="width:100px;height:177px;">
<img src="http://1.bp.blogspot.com/-9bo7lGHTAh4/VdZl0SJ3TNI/AAAAAAAACEU/VZwlu8nf97c/s1600/Screenshot_2015-08-20-13-03-32.png" alt="13"  style="width:100px;height:177px;">
<img src="http://2.bp.blogspot.com/-x0E4-9oIL8w/VdZl0gB13rI/AAAAAAAACEc/7CbdDKcYfes/s1600/Screenshot_2015-08-20-13-03-48.png" alt="14"  style="width:100px;height:177px;">


## Contribuições - As exigências são mínimas:
<li> As Classes devem seguir o mesmo padrão das classes modelo com javadoc</li>
<li> Faça um fork do projeto, implemente sua contribuição, faça um pool request para este projeto</li>

## Licença:
MIT License

## Autor:
<pre>
<b>Ricardo Ferreira</b>
Software Engineer at CESAR <a href="http://www.cesar.com.br">http://www.cesar.com.br</a>
Instagram: ricardo7307
Twitter: ricardo_7307
Blog: <a href="http://www.cleancodedevelopment-qualityseal.blogspot.com.br">http://www.cleancodedevelopment-qualityseal.blogspot.com.br</a>
</pre>
