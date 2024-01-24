<%@ include file="header.jsp" %>
  <body>
  <%@ include file="nav.jsp" %>
  <main id="main" class="main">
    <section class="section dashboard">
      <div class="row">
        <!-- Contenue -->
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Insertion  Look</h5>

            <!-- Floating Labels Form -->
            <form class="row g-3"action="../LookControleur" method="post" >
              <div class="col-md-12">
                <div class="form-floating">
                  <input type="text" class="form-control" id="floatingName" name="nomMatiere">
                  <label for="floatingName">Nom Look</label>
                </div>
              </div>
              <div class="text-center">
                <button type="submit" class="btn btn-primary">Valider</button>
              </div>
            </form><!-- End floating Labels Form -->

          </div>
        </div>

      </div>
    </section>

    <%@ include file="footer.jsp" %>
  </main>
</body>
</html>
