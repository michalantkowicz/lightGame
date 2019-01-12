<?xml version="1.0" encoding="UTF-8"?>
<tileset name="lightgame_tileset" tilewidth="148" tileheight="128" tilecount="19" columns="0">
 <grid orientation="orthogonal" width="1" height="1"/>
 <properties>
  <property name="textureName" value="tileFloor"/>
  <property name="tileClass" value="FLOOR"/>
 </properties>
 <tile id="0">
  <properties>
   <property name="tileClass" value="FLOOR"/>
  </properties>
  <image width="148" height="128" source="floor-tile.png"/>
 </tile>
 <tile id="1">
  <properties>
   <property name="tileClass" value="WALL"/>
  </properties>
  <image width="148" height="128" source="wall-tile.png"/>
 </tile>
 <tile id="2">
  <properties>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile.png"/>
 </tile>
 <tile id="3">
  <properties>
   <property name="tileClass" value="PLAYER"/>
  </properties>
  <image width="148" height="128" source="playerTile.png"/>
 </tile>
 <tile id="4">
  <properties>
   <property name="borderLightPosition" value="BOTTOM"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="BORDER_TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_bottom.png"/>
 </tile>
 <tile id="5">
  <properties>
   <property name="borderLightPosition" value="BOTTOM_LEFT"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="BORDER_TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_bottomLeft.png"/>
 </tile>
 <tile id="6">
  <properties>
   <property name="borderLightPosition" value="BOTTOM_RIGHT"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="BORDER_TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_bottomRight.png"/>
 </tile>
 <tile id="7">
  <properties>
   <property name="borderLightPosition" value="TOP"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="BORDER_TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_top.png"/>
 </tile>
 <tile id="8">
  <properties>
   <property name="borderLightPosition" value="TOP_LEFT"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="BORDER_TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_topLeft.png"/>
 </tile>
 <tile id="9">
  <properties>
   <property name="borderLightPosition" value="TOP_RIGHT"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="BORDER_TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_topRight.png"/>
 </tile>
 <tile id="10">
  <properties>
   <property name="lightColorHex" value="#24E91A"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_green.png"/>
 </tile>
 <tile id="11">
  <properties>
   <property name="lightColorHex" value="#1B4EBA"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_blue.png"/>
 </tile>
 <tile id="12">
  <properties>
   <property name="lightColorHex" value="#E32020"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_red.png"/>
 </tile>
 <tile id="13">
  <properties>
   <property name="lightColorHex" value="#B21BBA"/>
   <property name="lightType" value="TORCH"/>
   <property name="tileClass" value="TORCH_LIGHT"/>
  </properties>
  <image width="148" height="128" source="lightTile_violet.png"/>
 </tile>
 <tile id="14">
  <properties>
   <property name="tileClass" value="CHEST"/>
  </properties>
  <image width="148" height="128" source="chestTile.png"/>
 </tile>
 <tile id="15">
  <properties>
   <property name="tileClass" value="DOG"/>
  </properties>
  <image width="148" height="128" source="dogTile.png"/>
 </tile>
 <tile id="16">
  <properties>
   <property name="textureName" value="woodenFloorEven"/>
   <property name="tileClass" value="FLOOR"/>
  </properties>
  <image width="148" height="128" source="woodenFloorEven.png"/>
 </tile>
 <tile id="17">
  <properties>
   <property name="textureName" value="woodenFloorOdd"/>
   <property name="tileClass" value="FLOOR"/>
  </properties>
  <image width="148" height="128" source="woodenFloorOdd.png"/>
 </tile>
 <tile id="19">
  <properties>
   <property name="textureName" value="tileFloor"/>
   <property name="tileClass" value="FLOOR"/>
  </properties>
  <image width="148" height="128" source="tileFloor.png"/>
 </tile>
</tileset>
