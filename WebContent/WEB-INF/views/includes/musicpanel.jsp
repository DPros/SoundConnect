<%--
  Created by IntelliJ IDEA.
  User: citizenzer0
  Date: 4/11/16
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>

<div class="panel panel-info" id="party-panel">
    <div class="panel-heading">
        <h4 class="panel-title">
            <a id="collapse-conf" data-toggle="collapse" href="#collapse1">
                <span class="text-warning">Conference</span>
                <span class="glyphicon glyphicon-eye-open btn btn-link" id="conf-glyph"></span>
            </a>
        </h4>
    </div>
    <div id="collapse1" class="panel-collapse collapse in">
        <div class="panel-body">
                <div class="btn-group" id="main-player-btn-group">
                    <button type="button" class="btn btn-link" id="pause">
                        <span class="glyphicon glyphicon-pause btn btn-link" id="main-pause-glyph"></span>
                    </button>
                    <button type="button" class="mute button btn-link" id="mute">
                        <span class="glyphicon glyphicon-volume-off btn btn-link" id="mute-glyph"></span>
                    </button>
                </div>
             <!--   <div id="slider">
                    <input class="time-bar" id="rangeinput" type="range" value="0" onchange="rangevalue.value=value" />
                    <span class="highlight"></span>
                    <output id="rangevalue">0</output>
                </div>-->
			<audio id="main-player" src="" currentTime=""></audio>
            <div id="progressOut">
                <div id="progressIn">
                    <div id="time">00:00</div>
                </div>
            </div>
        </div>
        <div class="panel-footer scrollfixed" id="music-div">
            Insert track names here.
        </div>
    </div>
</div>