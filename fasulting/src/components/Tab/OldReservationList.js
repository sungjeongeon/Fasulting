import React from "react";
import SearchIcon from '@mui/icons-material/Search';
import { useState } from "react";
import LastReservationHo from "../Table/LastReservationHo"

function OldReservationList() {
  const [search, setSearch] = useState("")
  const onChange = (e) => setSearch(e.target.value)

  const onSubmit = (e) => {
    e.preventDefault()
    if (search === "") {
      return
    }
    setSearch("")
  }
  return (
    <div style={{width: "100%"}}>
      <form 
        style={{display:"flex", backgroundColor:"#F5F5F5", width: "70%", paddingLeft: "0.3rem", paddingRight: "0.3rem", borderRadius:"0.8rem"}}
        onSubmit={onSubmit}
      >
        <button style={{border: "none", cursor: "pointer", backgroundColor:"#F5F5F5", display:"flex", alignItems:"center"}}><SearchIcon/></button>
        <input 
          type="search" 
          placeholder="Search" 
          style={{ width:"100%", height:"2rem", border:"none", backgroundColor:"#F5F5F5", outline: "none"}}
          value={search}
          onChange={onChange}
        ></input>
      </form>
      <LastReservationHo/>
    </div>
  )
}

export default OldReservationList