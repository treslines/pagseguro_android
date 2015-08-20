# Pagseguro para Android
Integração de pagseguro para android sem usar os aplicativos nativos da pagseguro

## Introdução:
A maioria dos desenvolvedores sabem que a integração do pagseguro para android ainda é algo muito "chato". A integração com os aplicativos nativos da pagseguro ou wallet(carteira) obriga os desenvolvedores a ter os aplicativos
instalados e não funcionam perfeitamente. Me aventurando nessa linha, rapidamente percebi que este caminho é muito cheios de impecílios e gerava um monte de código de validação extra.

Noentando a pagseguro é bem popular e já tem um grau de maturidade interessante. Os consumidores brasileiros se sentem a vontade pagando com ela, pois a pagseguro tem todo um processo de validação e autorização de pagamentos, além de permitir a abertura de disputas em casos de fraude. Isso lhe torna um meio de pagamento indispensável no mercado brasileiro.

Foi pensando nessa necessidade, que decidi criar uma integração completa. Este aplicativo de demostração atende às necessidades mínimas do programador e em 90% dos casos é mais do que suficiente para a maioria dos aplicativos, que desejam vender algum tipo de pŕoduto. 

## Funções do aplicativo:
1) Oferece um modelo simples para criação de produtos de acordo com as especifiações da pagseguro

2) A criação de produtos valida as entradas a nível de programação para evitar erros durante a codificação

3) Prevê os cenários/fluxos de navegação em caso de cancelamento, sucesso e erro exibindo mensagens

4) Não requer nenhum aplicativo da pagseguro pre-instalado e realiza as transações atraves de webservices

5) Exibe progresso diretamente na actionbar e atualiza os titulos de acordo com as etapas do pagamento

## Bibliotecas usadas:
Para que o exemplo funcione, usamos uma biblioteca de REST bem popular usada pelo intagram, Pinterest e outros chamada; Android Asynchronous Http Client, que pode ser encontrada no link: http://loopj.com/android-async-http/

## Exemplo de uso (super simples):
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

## Imagens:
coming soon!

## Contribuições - As exigências são mínimas:
1) As Classes devem seguir o mesmo padrão das classes modelo com javadoc

2) Faça um fork do projeto, implemente sua contribuição, faça um pool request para este projeto

## Licença:
MIT License

## Autor:
Ricardo Ferreira

Software Engineer at CESAR

Instagram: ricardo7307

Twitter: ricardo_7307
