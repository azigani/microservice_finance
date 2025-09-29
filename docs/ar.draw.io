<mxfile host="app.diagrams.net" agent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36" version="28.2.4">
  <diagram id="micro-arch" name="Microservices Architecture">
    <mxGraphModel dx="1263" dy="585" grid="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" page="1" pageScale="1" pageWidth="2970" pageHeight="2100" math="0" shadow="0">
      <root>
        <mxCell id="0" />
        <mxCell id="1" parent="0" />
        <mxCell id="client" value="Client (Swagger UI)" style="shape=ellipse;fillColor=#dae8fc;strokeColor=#6c8ebf;" parent="1" vertex="1">
          <mxGeometry x="120" y="208" width="120" height="60" as="geometry" />
        </mxCell>
        <mxCell id="auth-service" value="Auth Service" style="shape=rectangle;rounded=1;fillColor=#e1d5e7;strokeColor=#9673a6;" parent="1" vertex="1">
          <mxGeometry x="760" y="268" width="130" height="80" as="geometry" />
        </mxCell>
        <mxCell id="account-service" value="Account Service&#xa;(Account creation, debit/credit)" style="shape=rectangle;rounded=1;fillColor=#fff2cc;strokeColor=#d6b656;" parent="1" vertex="1">
          <mxGeometry x="715" y="390" width="220" height="80" as="geometry" />
        </mxCell>
        <mxCell id="fraud-service" value="Fraud Service&#xa;(Kafka Consumer)" style="shape=rectangle;rounded=1;fillColor=#f8cecc;strokeColor=#b85450;" parent="1" vertex="1">
          <mxGeometry x="755" y="780" width="180" height="70" as="geometry" />
        </mxCell>
        <mxCell id="notification-service" value="Notification Service&#xa;(Email Channel)" style="shape=rectangle;rounded=1;fillColor=#cce5ff;strokeColor=#3399ff;" parent="1" vertex="1">
          <mxGeometry x="735" y="660" width="200" height="70" as="geometry" />
        </mxCell>
        <mxCell id="zipkin" value="Micrometer" style="shape=rectangle;rounded=1;fillColor=#fff2cc;strokeColor=#d6b656;" parent="1" vertex="1">
          <mxGeometry x="155.55" y="756" width="130" height="60" as="geometry" />
        </mxCell>
        <mxCell id="c1" style="entryX=0.029;entryY=0.632;entryDx=0;entryDy=0;entryPerimeter=0;" parent="1" source="client" target="uLBPM2dUGTbWOif0M4B2-8" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="320" y="250" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="c2" style="exitX=0.956;exitY=0.338;exitDx=0;exitDy=0;exitPerimeter=0;" parent="1" source="uLBPM2dUGTbWOif0M4B2-8" target="auth-service" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="540" y="238.21428571428578" as="sourcePoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-10" value="&lt;span style=&quot;font-size: 12px; background-color: rgb(236, 236, 236);&quot;&gt;JWT issuance,&amp;nbsp;&lt;/span&gt;&lt;br style=&quot;padding: 0px; margin: 0px; font-size: 12px; background-color: rgb(236, 236, 236);&quot;&gt;&lt;span style=&quot;font-size: 12px; background-color: rgb(236, 236, 236);&quot;&gt;Public Key verification&lt;/span&gt;" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="c2">
          <mxGeometry x="-0.0792" y="-1" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="c3" style="exitX=0.882;exitY=0.941;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0;entryY=0;entryDx=0;entryDy=0;" parent="1" source="uLBPM2dUGTbWOif0M4B2-8" target="transaction-service" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="514" y="290" as="sourcePoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-56" value="transactions API" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="c3">
          <mxGeometry x="-0.1553" y="2" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="c4" parent="1" source="transaction-service" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="831" y="470" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-4" value="FeignClient" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="c4">
          <mxGeometry x="-0.1294" y="-1" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="c5" parent="1" source="transaction-service" target="uLBPM2dUGTbWOif0M4B2-20" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="1210" y="499.4117647058823" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="c6" style="entryX=0.963;entryY=0.243;entryDx=0;entryDy=0;entryPerimeter=0;exitX=0.01;exitY=0.925;exitDx=0;exitDy=0;exitPerimeter=0;" parent="1" source="transaction-service" target="uLBPM2dUGTbWOif0M4B2-17" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="580" y="578.360655737705" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="c7" style="exitX=0;exitY=0.75;exitDx=0;exitDy=0;entryX=0.91;entryY=0.929;entryDx=0;entryDy=0;entryPerimeter=0;" parent="1" source="fraud-service" target="uLBPM2dUGTbWOif0M4B2-17" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="563.75" y="610" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="c9" style="entryX=0.067;entryY=0.411;entryDx=0;entryDy=0;entryPerimeter=0;exitX=1;exitY=0.147;exitDx=0;exitDy=0;exitPerimeter=0;" parent="1" source="uLBPM2dUGTbWOif0M4B2-8" target="uLBPM2dUGTbWOif0M4B2-5" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="361.32887022172235" y="290" as="sourcePoint" />
            <mxPoint x="230" y="370" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-6" value="Rate Limiter" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="c9">
          <mxGeometry x="-0.0164" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-2" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.971;exitY=0.75;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0;entryY=0;entryDx=0;entryDy=0;" edge="1" parent="1" source="uLBPM2dUGTbWOif0M4B2-8" target="account-service">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="420.98" y="291.03999999999996" as="sourcePoint" />
            <mxPoint x="750" y="410" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-12" value="accounts API" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="uLBPM2dUGTbWOif0M4B2-2">
          <mxGeometry x="-0.1197" y="2" relative="1" as="geometry">
            <mxPoint x="1" as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-5" value="" style="points=[];aspect=fixed;html=1;align=center;shadow=0;dashed=0;fillColor=#FF6A00;strokeColor=none;shape=mxgraph.alibaba_cloud.redis_kvstore;" vertex="1" parent="1">
          <mxGeometry x="780" y="190" width="63.41" height="50" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-7" value="Redis" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="780" y="159.99999999999997" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-8" value="" style="image;aspect=fixed;html=1;points=[];align=center;fontSize=12;image=img/lib/azure2/compute/Azure_Spring_Cloud.svg;" vertex="1" parent="1">
          <mxGeometry x="395" y="200" width="68" height="68" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-9" value="&lt;span style=&quot;text-wrap-mode: nowrap;&quot;&gt;Spring Cloud Gateway&lt;/span&gt;&lt;div&gt;&lt;span style=&quot;text-wrap-mode: nowrap;&quot;&gt;(Circuit Breaker,&amp;nbsp;Fallback)&lt;/span&gt;&lt;/div&gt;" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="358.5" y="160" width="141" height="40" as="geometry" />
        </mxCell>
        <mxCell id="transaction-service" value="Transaction Service&#xa;(Kafka Producer, Idempotency Key)" style="shape=rectangle;rounded=1;fillColor=#d5e8d4;strokeColor=#82b366;" parent="1" vertex="1">
          <mxGeometry x="705" y="530" width="260" height="80" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-13" value="" style="endArrow=classic;html=1;rounded=0;exitX=1;exitY=0.5;exitDx=0;exitDy=0;" edge="1" parent="1" source="account-service" target="uLBPM2dUGTbWOif0M4B2-20">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="700" y="450" as="sourcePoint" />
            <mxPoint x="1260" y="460" as="targetPoint" />
            <Array as="points">
              <mxPoint x="1100" y="530" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-15" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.995;exitY=0.4;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="notification-service" target="uLBPM2dUGTbWOif0M4B2-20">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="700" y="590" as="sourcePoint" />
            <mxPoint x="1225" y="517" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-16" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.989;exitY=0.414;exitDx=0;exitDy=0;exitPerimeter=0;" edge="1" parent="1" source="fraud-service" target="uLBPM2dUGTbWOif0M4B2-20">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="700" y="600" as="sourcePoint" />
            <mxPoint x="1260" y="520" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-17" value="" style="points=[];aspect=fixed;html=1;align=center;shadow=0;dashed=0;fillColor=#FF6A00;strokeColor=none;shape=mxgraph.alibaba_cloud.kafka;" vertex="1" parent="1">
          <mxGeometry x="480" y="778" width="94.5" height="70" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-19" value="Kafka" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="487.25" y="748.000655737705" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-20" value="" style="sketch=0;outlineConnect=0;fontColor=#232F3E;gradientColor=none;fillColor=#C925D1;strokeColor=none;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;pointerEvents=1;shape=mxgraph.aws4.rds_postgresql_instance;" vertex="1" parent="1">
          <mxGeometry x="1140" y="600" width="78" height="78" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-21" value="" style="endArrow=classic;html=1;rounded=0;exitX=1;exitY=0.75;exitDx=0;exitDy=0;" edge="1" parent="1" source="auth-service" target="uLBPM2dUGTbWOif0M4B2-20">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="700" y="470" as="sourcePoint" />
            <mxPoint x="750" y="420" as="targetPoint" />
            <Array as="points">
              <mxPoint x="1180" y="328" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-22" value="" style="points=[];aspect=fixed;html=1;align=center;shadow=0;dashed=0;fillColor=#FF6A00;strokeColor=none;shape=mxgraph.alibaba_cloud.prometheus;" vertex="1" parent="1">
          <mxGeometry x="205.55" y="456" width="48.9" height="48.9" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-23" value="" style="image;aspect=fixed;html=1;points=[];align=center;fontSize=12;image=img/lib/azure2/other/Grafana.svg;" vertex="1" parent="1">
          <mxGeometry x="192.00000000000006" y="567.2" width="68" height="52.800000000000004" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-24" value="Prometheus" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="200.00000000000006" y="506" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-25" value="Grafana" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="196.00000000000006" y="620" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-26" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.5;exitY=1;exitDx=0;exitDy=0;entryX=0.56;entryY=-0.019;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="uLBPM2dUGTbWOif0M4B2-24" target="uLBPM2dUGTbWOif0M4B2-23">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="685.55" y="496" as="sourcePoint" />
            <mxPoint x="735.55" y="446" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-27" value="" style="swimlane;startSize=0;" vertex="1" parent="1">
          <mxGeometry x="135.55" y="450" width="180" height="200" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-28" value="System Monitor" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="200" y="420" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-29" value="" style="endArrow=classic;html=1;rounded=0;exitX=0;exitY=0.25;exitDx=0;exitDy=0;entryX=0.992;entryY=0.429;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="transaction-service" target="uLBPM2dUGTbWOif0M4B2-22">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="630" y="540" as="sourcePoint" />
            <mxPoint x="680" y="490" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-33" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.003;exitY=0.171;exitDx=0;exitDy=0;exitPerimeter=0;entryX=0.685;entryY=0.716;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="fraud-service" target="uLBPM2dUGTbWOif0M4B2-22">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="630" y="640" as="sourcePoint" />
            <mxPoint x="210" y="510" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-34" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.153;exitY=0.486;exitDx=0;exitDy=0;exitPerimeter=0;entryX=1;entryY=0.25;entryDx=0;entryDy=0;" edge="1" parent="1" source="uLBPM2dUGTbWOif0M4B2-17" target="uLBPM2dUGTbWOif0M4B2-24">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="630" y="730" as="sourcePoint" />
            <mxPoint x="680" y="680" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-35" value="Kafka JMX Exporter" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="uLBPM2dUGTbWOif0M4B2-34">
          <mxGeometry x="-0.1412" y="-1" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-36" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.5;exitY=1;exitDx=0;exitDy=0;entryX=0.5;entryY=0;entryDx=0;entryDy=0;" edge="1" parent="1" source="transaction-service" target="notification-service">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="630" y="620" as="sourcePoint" />
            <mxPoint x="680" y="570" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-37" value="FeignClient" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="uLBPM2dUGTbWOif0M4B2-36">
          <mxGeometry x="0.04" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-38" value="Zipkin" style="shape=rectangle;rounded=1;fillColor=#e1d5e7;strokeColor=#9673a6;" vertex="1" parent="1">
          <mxGeometry x="155.55" y="866" width="130" height="60" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-39" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.5;exitY=1;exitDx=0;exitDy=0;entryX=0.5;entryY=0;entryDx=0;entryDy=0;" edge="1" parent="1" source="zipkin" target="uLBPM2dUGTbWOif0M4B2-38">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="685.55" y="876" as="sourcePoint" />
            <mxPoint x="735.55" y="826" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-40" value="" style="endArrow=classic;html=1;rounded=0;exitX=0;exitY=0.5;exitDx=0;exitDy=0;entryX=1;entryY=0.25;entryDx=0;entryDy=0;" edge="1" parent="1" source="transaction-service" target="zipkin">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="630" y="830" as="sourcePoint" />
            <mxPoint x="680" y="780" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-41" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.25;exitY=1;exitDx=0;exitDy=0;entryX=0.973;entryY=0.9;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="1" source="fraud-service" target="zipkin">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="630" y="830" as="sourcePoint" />
            <mxPoint x="680" y="780" as="targetPoint" />
            <Array as="points">
              <mxPoint x="530" y="910" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-42" value="" style="swimlane;startSize=0;" vertex="1" parent="1">
          <mxGeometry x="130" y="744" width="185.55" height="200" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-43" value="Tracing" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="185.55" y="714" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-44" value="" style="shape=rect;fillColor=#0F62FE;aspect=fixed;resizable=0;labelPosition=center;verticalLabelPosition=bottom;align=center;verticalAlign=top;strokeColor=none;fontSize=14;" vertex="1" parent="1">
          <mxGeometry x="179.55" y="1038" width="48" height="48" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-45" value="" style="fillColor=#ffffff;strokeColor=none;dashed=0;outlineConnect=0;html=1;labelPosition=center;verticalLabelPosition=bottom;verticalAlign=top;part=1;movable=0;resizable=0;rotatable=0;shape=mxgraph.ibm_cloud.database--elastic" vertex="1" parent="uLBPM2dUGTbWOif0M4B2-44">
          <mxGeometry width="24" height="24" relative="1" as="geometry">
            <mxPoint x="12" y="12" as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-47" value="" style="sketch=0;outlineConnect=0;fontColor=#232F3E;gradientColor=none;fillColor=#E7157B;strokeColor=none;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;pointerEvents=1;shape=mxgraph.aws4.cloudwatch_logs;" vertex="1" parent="1">
          <mxGeometry x="167.55" y="1166" width="78" height="58" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-48" value="kibana" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="167.55" y="1236" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-49" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.5;exitY=1;exitDx=0;exitDy=0;" edge="1" parent="1" source="uLBPM2dUGTbWOif0M4B2-51" target="uLBPM2dUGTbWOif0M4B2-47">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="685.55" y="1096" as="sourcePoint" />
            <mxPoint x="735.55" y="1046" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-53" value="logstash" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="uLBPM2dUGTbWOif0M4B2-49">
          <mxGeometry x="-0.442" y="-2" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-51" value="Elasticsearch" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="175.55" y="1086" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-52" value="" style="endArrow=classic;html=1;rounded=0;exitX=0.113;exitY=0.988;exitDx=0;exitDy=0;exitPerimeter=0;entryX=1;entryY=0.5;entryDx=0;entryDy=0;" edge="1" parent="1" source="transaction-service" target="uLBPM2dUGTbWOif0M4B2-44">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="630" y="780" as="sourcePoint" />
            <mxPoint x="680" y="730" as="targetPoint" />
            <Array as="points">
              <mxPoint x="600" y="1066" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-54" value="" style="swimlane;startSize=0;" vertex="1" parent="1">
          <mxGeometry x="130" y="1026" width="185.55" height="250" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-55" value="System Logs" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="185.55" y="996" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="uLBPM2dUGTbWOif0M4B2-57" value="&lt;h1 style=&quot;margin-top: 0px;&quot;&gt;FinPay Microservice Architecture&lt;/h1&gt;" style="text;html=1;whiteSpace=wrap;overflow=hidden;rounded=0;" vertex="1" parent="1">
          <mxGeometry x="480" y="40" width="390" height="50" as="geometry" />
        </mxCell>
      </root>
    </mxGraphModel>
  </diagram>
</mxfile>
