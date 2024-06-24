using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PinkEffectController : MonoBehaviour
{
    public GameObject pinkEffectPrefab;

    public void TriggerPinkEffect(Vector3 position)
    {
        GameObject pinkEffect = Instantiate(pinkEffectPrefab, position, Quaternion.identity);
        pinkEffect.GetComponent<ParticleSystem>().Play();
        Destroy(pinkEffect, 2f); // Destroy after 2 seconds
    }



    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
