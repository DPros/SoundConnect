<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/19/16
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<div>
    <div class="row center-block">
        <p class="text-primary profile-text">Hi, <b>${usernamelabel }</b>!</p>
    </div>
    <div class="input-group row center-block">
        <form action="changename" method="post" id="change-username">
            <input type="text" class="search-query form-control" placeholder="Change your name" name="query" />
            <span class="input-group-btn">
                <button type="submit" class="btn btn-link" id="changebtn">
                    <span class="glyphicon glyphicon-ok"></span>
                </button>
            </span>
        </form>
    </div>
    <div class="row"></div>
    <div class="row center-block profile-text">
        <p class="text-primary">Create a new conference</p>
    </div>
    <div class="input-group row center-block">
        <form action="createconference" method="post" id="create-conference">
            <input type="text" class="search-query form-control" placeholder="Enter conference name" name="query" />
            <span class="input-group-btn">
                <button type="submit" class="btn btn-link" id="confbtn">
                    <span class="glyphicon glyphicon-ok"></span>
                </button>
            </span>
        </form>
    </div>


</div>
