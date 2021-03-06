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
            <div class="snd">
                <div class="toggle">
                    <!--<button type="button" class="button btn-default toggle-play">Play</button>-->
                    <button type="button" class="mute button btn-default">
                        <span class="glyphicon glyphicon-volume-off btn btn-link" id="mute-glyph"></span>
                    </button>
                </div>
                <div id="slider">
                    <input class="time-bar" id="rangeinput" type="range" value="0" onchange="rangevalue.value=value" />
                    <span class="highlight"></span>
                    <output id="rangevalue">0</output>
                </div>

              <!--  <span class="currenttime">00:00</span>/
                <span class="duration">00:00</span>-->
            </div>

        </div>
        <div class="panel-footer scrollfixed" id="music-div">
            Insert track names here.
        </div>
    </div>
</div>