<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/explorarComprador.css">
  <title>Explorar</title>
</head>

<body>

  <div class="TextoI">
    <h3>Imagenes</h3>
  </div>

  <div class="parent">


        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <div id="images" class="card-img-top"></div>
                <script>
                    var imagesDiv = document.getElementById("images");

                    fetch("art")
                        .then(response => response.json())
                        .then(images => {
                            images.map(image => {
                                // Creating the image element in DOM
                                let imgElem = document.createElement("img");
                                imgElem.src = "./" + image;
                                imgElem.width = 200;

                                // Attaching element to DIV
                                imagesDiv.appendChild(imgElem);
                            });
                        });
                </script>
                <div class="card-body">
                    <p class="card-text">Titulo: <%=request.getAttribute("title")%></p>
                    <p class="card-text">Autor: <%=request.getAttribute("author")%></p>
                    <p class="card-text">Precio: <%=request.getAttribute("price")%> Fcoins</p>
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>
        <div class="col" style="padding-right: 10px; ">
            <div class="card" style="width: 18rem;">
                <img src="" class="card-img-top" alt="">
                <div class="card-body">
                </div>
              </div>
        </div>

  </div>

</body>

</html>